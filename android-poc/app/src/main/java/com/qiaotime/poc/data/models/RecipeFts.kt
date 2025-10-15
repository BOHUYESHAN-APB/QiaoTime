package com.qiaotime.poc.data.models

import androidx.room.Fts4

@Fts4(contentEntity = Recipe::class)
data class RecipeFts(
    val title: String,
    val mdPath: String
)
