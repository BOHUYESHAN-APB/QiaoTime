package com.qiaotime.poc.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.qiaotime.poc.data.dao.FavoriteDao
import com.qiaotime.poc.data.dao.RecipeDao
import com.qiaotime.poc.data.models.Favorite
import com.qiaotime.poc.data.models.Recipe

@Database(entities = [Recipe::class, Favorite::class, com.qiaotime.poc.data.models.RecipeFts::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private const val DB_NAME = "qiaotime_poc.db"

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
