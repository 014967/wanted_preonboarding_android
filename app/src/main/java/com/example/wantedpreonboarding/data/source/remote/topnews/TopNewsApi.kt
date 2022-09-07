package com.example.wantedpreonboarding.data.source.remote.topnews

import com.example.wantedpreonboarding.data.model.TopNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Created by 김현국 2022/09/07
 */
interface TopNewsApi {
    @GET("/v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String
    ): Response<TopNews>
}
