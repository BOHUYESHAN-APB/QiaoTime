package com.qiaotime.poc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.qiaotime.poc.data.AppDatabase
import com.qiaotime.poc.data.models.Recipe
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_with_search)

        // Markwon removed for compatibility; we'll render markdown to HTML using CommonMark
        val parser = Parser.builder().build()
        val renderer = HtmlRenderer.builder().build()

        val db = AppDatabase.getInstance(this)

        val recycler: RecyclerView = findViewById(R.id.recyclerView)
        val searchInput: EditText = findViewById(R.id.searchInput)
        val searchBtn: Button = findViewById(R.id.searchBtn)

        val favoritesSet = mutableSetOf<Long>()

        val adapter = com.qiaotime.poc.ui.RecipeAdapter(onFavToggle = { recipe, isFav ->
            ioScope.launch {
                if (isFav) {
                    db.favoriteDao().add(com.qiaotime.poc.data.models.Favorite(recipe.id, System.currentTimeMillis()))
                    favoritesSet.add(recipe.id)
                } else {
                    db.favoriteDao().remove(recipe.id)
                    favoritesSet.remove(recipe.id)
                }
            }
        }, onClick = { recipe ->
            // show recipe in a dialog with clickable images
            ioScope.launch {
                val content = assets.open(recipe.mdPath).bufferedReader().use(BufferedReader::readText)
                withContext(Dispatchers.Main) {
                    showRecipeDialog(recipe.title, content)
                }
            }
        })

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // Bulk import from assets if DB empty
        ioScope.launch {
            val importer = com.qiaotime.poc.importer.AssetImporter(this@MainActivity, db)
            importer.importRecipesIfEmpty()

            // load favorites
            val favs = db.favoriteDao().listAll()
            for (f in favs) favoritesSet.add(f.recipeId)

            val list = db.recipeDao().listAll()
            withContext(Dispatchers.Main) {
                adapter.submitList(list)
            }
        }

        searchBtn.setOnClickListener {
            val q = searchInput.text.toString().trim()
            if (q.isEmpty()) return@setOnClickListener
            ioScope.launch {
                // Use FTS: prefix match
                val ftsQuery = "$q*"
                val results = try {
                    db.recipeDao().searchFts(ftsQuery)
                } catch (e: Exception) {
                    // fallback to LIKE
                    db.recipeDao().searchByTitle(q)
                }
                withContext(Dispatchers.Main) {
                    adapter.submitList(results)
                }
            }
        }
    }

    private fun showRecipeDialog(title: String, mdContent: String) {
        // Use WebView to render HTML converted from Markdown.
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this).create()
        val web = android.webkit.WebView(this)
        web.settings.javaScriptEnabled = true
        // Expose a JS bridge that will notify Android when an image is clicked
        web.addJavascriptInterface(object {
            @android.webkit.JavascriptInterface
            fun openImage(path: String) {
                val intent = android.content.Intent(this@MainActivity, ImagePreviewActivity::class.java)
                intent.putExtra("path", path)
                startActivity(intent)
                dialog.dismiss()
            }
        }, "AndroidBridge")

        // Convert markdown to HTML and add JS handler for images
        val node = parser.parse(mdContent)
        val html = renderer.render(node)
        // Inject simple JS to intercept image clicks and call Android bridge
        val htmlWithJs = """
            <html>
            <head>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <style>img{max-width:100%;height:auto}</style>
            <script>
              function wrapImages(){
                document.querySelectorAll('img').forEach(function(img){
                  img.onclick = function(){ AndroidBridge.openImage(img.getAttribute('src')) }
                })
              }
              window.onload = wrapImages
            </script>
            </head>
            <body>$html</body>
            </html>
        """.trimIndent()

        web.loadDataWithBaseURL("file:///android_asset/", htmlWithJs, "text/html", "utf-8", null)

        dialog.setTitle(title)
        dialog.setView(web)
        dialog.show()
    }
}
