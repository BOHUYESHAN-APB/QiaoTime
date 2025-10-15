package com.qiaotime.poc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qiaotime.poc.data.models.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(fav: Favorite)

    @Query("SELECT * FROM favorites ORDER BY addedAt DESC")
    suspend fun listAll(): List<Favorite>

    @Query("DELETE FROM favorites WHERE recipeId = :recipeId")
    suspend fun remove(recipeId: Long)
}
