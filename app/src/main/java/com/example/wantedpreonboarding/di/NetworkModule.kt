package com.example.wantedpreonboarding.di

import com.example.wantedpreonboarding.BuildConfig
import com.example.wantedpreonboarding.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/09/07
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val baseUrl = "https://newsapi.org"

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttp3Client(networkInterceptor: NetworkInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(
            networkInterceptor
        )
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
