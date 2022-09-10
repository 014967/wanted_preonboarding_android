package com.example.wantedpreonboarding.domain.model

/**
 * @Created by 김현국 2022/09/07
 */
data class TopNews(
    val title: String,
    val author: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val description: String
)
