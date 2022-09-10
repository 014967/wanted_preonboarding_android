package com.example.wantedpreonboarding.data.model

import com.example.wantedpreonboarding.data.source.local.entity.ArticleEntity
import com.example.wantedpreonboarding.domain.model.TopNews

/**
 * @Created by 김현국 2022/09/09
 */
fun TopNews.asEntity() = ArticleEntity(
    title = title,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    urlToImage = imageUrl
)

fun ArticleEntity.asModel() = TopNews(
    title = title,
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    publishedAt = publishedAt,
    imageUrl = urlToImage ?: ""
)
