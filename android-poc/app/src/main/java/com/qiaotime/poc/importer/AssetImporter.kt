package com.qiaotime.poc.importer

import android.content.Context
import com.qiaotime.poc.data.AppDatabase
import com.qiaotime.poc.data.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetImporter(private val context: Context, private val db: AppDatabase) {

    suspend fun importRecipesIfEmpty() = withContext(Dispatchers.IO) {
        val existing = db.recipeDao().listAll()
        if (existing.isNotEmpty()) return@withContext

        val base = "recipes"
        val mdFiles = mutableListOf<String>()

        fun scan(path: String) {
            val list = try {
                context.assets.list(path) ?: arrayOf()
            } catch (e: Exception) {
                arrayOf<String>()
            }
            if (list.isEmpty()) return
            for (entry in list) {
                val childPath = if (path.isEmpty()) entry else "$path/$entry"
                if (entry.endsWith(".md", ignoreCase = true)) {
                    mdFiles.add(childPath)
                } else {
                    // check if it's a directory by trying to list
                    val sub = try { context.assets.list(childPath) } catch (e: Exception) { null }
                    if (sub != null && sub.isNotEmpty()) scan(childPath)
                }
            }
        }

        scan(base)

        for (md in mdFiles) {
            val dir = md.substringBeforeLast('/', "")
            val filename = md.substringAfterLast('/')
            val title = filename.removeSuffix(".md")
            val slug = title
            val coverCandidate = if (dir.isNotEmpty()) "$dir/image.jpg" else "image.jpg"
            val coverExists = try {
                context.assets.open(coverCandidate).close(); true
            } catch (e: Exception) { false }

            val recipe = Recipe(
                title = title,
                slug = slug,
                mdPath = md,
                coverImagePath = if (coverExists) coverCandidate else null
            )

            try {
                db.recipeDao().insert(recipe)
            } catch (e: Exception) {
                // ignore single insert failures in POC
            }
        }
    }
}
