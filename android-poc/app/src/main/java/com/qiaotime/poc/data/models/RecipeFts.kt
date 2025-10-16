package com.qiaotime.poc.data.models

import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "RecipeFts")
@Fts4(contentEntity = Recipe::class)
data class RecipeFts(
    val title: String,
    val mdPath: String
)
