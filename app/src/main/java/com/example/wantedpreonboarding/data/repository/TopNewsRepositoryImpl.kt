package com.example.wantedpreonboarding.data.repository

import com.example.wantedpreonboarding.data.mapper.mappingTopNewsResponseToDomainModel
import com.example.wantedpreonboarding.data.source.remote.topnews.TopNewsRemoteDataSource
import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.model.TopNews
import com.example.wantedpreonboarding.domain.repository.TopNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/07
 */
class TopNewsRepositoryImpl @Inject constructor(
    private val topNewsRemoteDataSource: TopNewsRemoteDataSource
) : TopNewsRepository {
    override fun getTopNews(category: String): Flow<Results<List<TopNews>>> {
        return flow {
            val response = topNewsRemoteDataSource.getTopNews(country = "us", category = category)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if (body.status == "ok") {
                    val data = mappingTopNewsResponseToDomainModel(body)
                    emit(Results.Success(data))
                }
            }
        }
    }
}
