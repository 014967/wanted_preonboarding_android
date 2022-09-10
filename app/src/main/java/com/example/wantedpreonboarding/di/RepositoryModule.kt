package com.example.wantedpreonboarding.di

import com.example.wantedpreonboarding.data.repository.NewsDetailRepositoryImpl
import com.example.wantedpreonboarding.data.repository.SavedNewsRepositoryImpl
import com.example.wantedpreonboarding.data.repository.TopNewsRepositoryImpl
import com.example.wantedpreonboarding.domain.repository.NewsDetailRepository
import com.example.wantedpreonboarding.domain.repository.SavedNewsRepository
import com.example.wantedpreonboarding.domain.repository.TopNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/09/07
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideTopNewsRepository(impl: TopNewsRepositoryImpl): TopNewsRepository

    @Singleton
    @Binds
    abstract fun provideNewsDetailRepository(impl: NewsDetailRepositoryImpl): NewsDetailRepository

    @Singleton
    @Binds
    abstract fun provideSavedNewsRepository(impl: SavedNewsRepositoryImpl): SavedNewsRepository
}
