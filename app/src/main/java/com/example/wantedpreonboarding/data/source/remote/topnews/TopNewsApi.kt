package com.example.wantedpreonboarding.data.source.remote.topnews

import com.example.wantedpreonboarding.data.source.remote.model.TopNews
import retrofit2.Response
import retrofit2.http.GET

/**
 * @Created by 김현국 2022/09/07
 */
interface TopNewsApi {
    @GET("/v2/top-headlines")
    suspend fun getTopNews(): Response<TopNews>
}