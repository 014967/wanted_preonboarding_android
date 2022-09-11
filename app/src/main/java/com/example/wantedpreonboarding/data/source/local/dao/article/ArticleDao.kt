package com.example.wantedpreonboarding.data.source.local.dao.article

import androidx.room.*
import com.example.wantedpreonboarding.data.source.local.entity.ArticleEntity

/**
 * @Created by 김현국 2022/09/09
 */
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(article: ArticleEntity): Long

    @Query("SELECT * FROM articles JOIN articlesFts ON (articles.title = articlesFts.title) WHERE articlesFts MATCH :query LIMIT 1")
    suspend fun searchSavedArticle(query: String?): ArticleEntity?

    @Query("DELETE  FROM articles  WHERE articles.title = :title")
    suspend fun delete(title: String): Int

    @Query("SELECT * FROM articles")
    suspend fun getAllSavedArticles(): List<ArticleEntity>
}
