package com.example.wantedpreonboarding.di

import com.example.wantedpreonboarding.data.source.remote.topnews.TopNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/09/07
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideTopNewsRemoteApi(retrofit: Retrofit): TopNewsApi {
        return retrofit.create(TopNewsApi::class.java)
    }
}
