package com.qiaotime.poc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qiaotime.poc.data.models.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe): Long

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getById(id: Long): Recipe?

    @Query("SELECT id, title, slug, mdPath, coverImagePath FROM recipes ORDER BY id DESC")
    suspend fun listAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :q || '%' ORDER BY id DESC")
    suspend fun searchByTitle(q: String): List<Recipe>

    // FTS-based search (requires FTS table)
    @Query("SELECT recipes.* FROM recipes JOIN RecipeFts ON recipes.rowid = RecipeFts.rowid WHERE RecipeFts MATCH :q")
    suspend fun searchFts(q: String): List<Recipe>
}
