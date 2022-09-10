package com.example.wantedpreonboarding.data.repository

import com.example.wantedpreonboarding.data.model.asEntity
import com.example.wantedpreonboarding.data.source.local.dao.article.ArticleLocalDataSource
import com.example.wantedpreonboarding.domain.model.TopNews
import com.example.wantedpreonboarding.domain.repository.NewsDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/09
 */
class NewsDetailRepositoryImpl @Inject constructor(
    private val articleLocalDataSource: ArticleLocalDataSource
) : NewsDetailRepository {
    override fun saveNews(topNews: TopNews): Flow<Long> {
        // top new to entity
        return flow {
            val save = articleLocalDataSource.savedArticle(articleEntity = topNews.asEntity())
            emit(save)
        }
    }

    override fun findSavedNews(title: String): Flow<Boolean> {
        return flow {
            val result = articleLocalDataSource.findArticleWithTitle(articleTitle = title)
            if (result == null) {
                emit(false)
            } else {
                emit(true)
            }
        }
    }

    override fun deleteNews(title: String): Flow<Int> {
        return flow {
            val delete = articleLocalDataSource.deleteArticle(articleTitle = title)
            emit(delete)
        }
    }
}
