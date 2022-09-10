package com.example.wantedpreonboarding.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Created by 김현국 2022/09/09
 */

@Entity(
    tableName = "articles"
)
data class ArticleEntity(

    @PrimaryKey
    val title: String,

    val author: String?,

    val content: String?,

    val description: String?,

    val publishedAt: String,

    val urlToImage: String?

)
