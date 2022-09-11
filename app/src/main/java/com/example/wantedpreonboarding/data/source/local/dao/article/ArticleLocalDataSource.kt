package com.example.wantedpreonboarding.data.source.local.dao.article

import com.example.wantedpreonboarding.data.source.local.entity.ArticleEntity
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/09
 */
class ArticleLocalDataSource @Inject constructor(
    private val articleDao: ArticleDao
) {
    suspend fun savedArticle(articleEntity: ArticleEntity): Long {
        return articleDao.insert(
            article = articleEntity
        )
    }

    suspend fun findArticleWithTitle(articleTitle: String): ArticleEntity? {
        return articleDao.searchSavedArticle(query = articleTitle)
    }

    suspend fun deleteArticle(articleTitle: String): Int {
        return articleDao.delete(title = articleTitle)
    }

    suspend fun getAllSavedArticles(): List<ArticleEntity> {
        return articleDao.getAllSavedArticles()
    }
}
