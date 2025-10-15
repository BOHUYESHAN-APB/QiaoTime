package com.qiaotime.poc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import io.noties.markwon.Markwon
import io.noties.markwon.image.coil.CoilImagesPlugin
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

        val markwon = Markwon.builder(this)
            .usePlugin(CoilImagesPlugin.create(this))
            .build()

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
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this).create()
        val tv = TextView(this)
        tv.setPadding(20, 20, 20, 20)

        val markwon = Markwon.builder(this)
            .usePlugin(CoilImagesPlugin.create(this))
            .build()

        markwon.setMarkdown(tv, mdContent)

        // set movement method to allow clickable spans
        tv.movementMethod = android.text.method.LinkMovementMethod.getInstance()

        // attach touch listener to detect image clicks
        tv.setOnTouchListener { v, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                val x = event.x.toInt() - v.paddingLeft
                val y = event.y.toInt() - v.paddingTop
                val layout = (v as TextView).layout ?: return@setOnTouchListener false
                val line = layout.getLineForVertical(y)
                val off = layout.getOffsetForHorizontal(line, x.toFloat())
                val sp = v.text as? android.text.Spanned ?: return@setOnTouchListener false
                val imgs = sp.getSpans(off, off, android.text.style.ImageSpan::class.java)
                if (imgs != null && imgs.isNotEmpty()) {
                    val img = imgs[0]
                    val src = img.source // Markwon sets source to the image path
                    if (!src.isNullOrEmpty()) {
                        val intent = android.content.Intent(this, ImagePreviewActivity::class.java)
                        // If src is an asset path like recipes/... ensure asset URI
                        val assetPath = if (src.startsWith("/")) src.removePrefix("/") else src
                        intent.putExtra("path", assetPath)
                        startActivity(intent)
                        dialog.dismiss()
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        dialog.setTitle(title)
        dialog.setView(tv)
        dialog.show()
    }
}
