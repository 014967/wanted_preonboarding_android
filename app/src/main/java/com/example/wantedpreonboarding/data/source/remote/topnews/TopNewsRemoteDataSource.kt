package com.example.wantedpreonboarding.data.source.remote.topnews

import com.example.wantedpreonboarding.data.source.remote.model.TopNews
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/07
 */
class TopNewsRemoteDataSource @Inject constructor(
    private val topNewsApi: TopNewsApi
) {
    suspend fun getTopNews(): Response<TopNews> {
        return topNewsApi.getTopNews()
    }
}
