package com.example.wantedpreonboarding.di

import com.example.wantedpreonboarding.domain.repository.NewsDetailRepository
import com.example.wantedpreonboarding.domain.repository.SavedNewsRepository
import com.example.wantedpreonboarding.domain.repository.TopNewsRepository
import com.example.wantedpreonboarding.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/09/07
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetTopNewsUseCase(topNewsRepository: TopNewsRepository): GetTopNewsUseCase {
        return GetTopNewsUseCase(topNewsRepository = topNewsRepository)
    }

    @Provides
    @Singleton
    fun provideInsertNewsUseCase(newsDetailRepository: NewsDetailRepository): InsertNewsUseCase {
        return InsertNewsUseCase(newsDetailRepository = newsDetailRepository)
    }

    @Provides
    @Singleton
    fun provideFindSavedNewsWithTitleUseCase(newsDetailRepository: NewsDetailRepository): FindSavedNewsWithTitleUseCase {
        return FindSavedNewsWithTitleUseCase(newsDetailRepository = newsDetailRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSavedNewsUseCase(newsDetailRepository: NewsDetailRepository): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(newsDetailRepository = newsDetailRepository)
    }

    @Provides
    @Singleton
    fun provideGetSavedTopNewsListUseCase(savedNewsRepository: SavedNewsRepository): GetSavedTopNewsListUseCase {
        return GetSavedTopNewsListUseCase(savedNewsRepository = savedNewsRepository)
    }
}
