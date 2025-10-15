package com.qiaotime.poc.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val slug: String,
    val mdPath: String,
    val coverImagePath: String?
)
