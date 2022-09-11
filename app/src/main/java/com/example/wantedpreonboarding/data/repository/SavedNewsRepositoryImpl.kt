package com.example.wantedpreonboarding.data.repository

import com.example.wantedpreonboarding.data.model.asModel
import com.example.wantedpreonboarding.data.source.local.dao.article.ArticleLocalDataSource
import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.model.TopNews
import com.example.wantedpreonboarding.domain.repository.SavedNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/10
 */
class SavedNewsRepositoryImpl @Inject constructor(
    private val articleLocalDataSource: ArticleLocalDataSource
) : SavedNewsRepository {
    override fun getSavedNewsList(): Flow<Results<List<TopNews>>> {
        return flow {
            emit(Results.Loading)
            val result = articleLocalDataSource.getAllSavedArticles()
            if (result.isNotEmpty()) {
                val data = result.map { entity ->
                    entity.asModel()
                }
                emit(Results.Success(data))
            }
        }
    }
}
