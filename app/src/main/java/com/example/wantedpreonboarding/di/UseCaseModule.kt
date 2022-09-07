package com.example.wantedpreonboarding.di

import com.example.wantedpreonboarding.domain.repository.TopNewsRepository
import com.example.wantedpreonboarding.domain.usecase.GetTopNewsUseCase
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
}
