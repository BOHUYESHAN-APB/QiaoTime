package com.qiaotime.poc.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val recipeId: Long,
    val addedAt: Long
)
