package com.example.wantedpreonboarding.data.source.local.entity

import androidx.room.Entity
import androidx.room.Fts4

/**
 * @Created by 김현국 2022/09/09
 */

@Entity(tableName = "articlesFts")
@Fts4(contentEntity = ArticleEntity::class)
data class ArticleFtsEntity(
    val title: String
)
