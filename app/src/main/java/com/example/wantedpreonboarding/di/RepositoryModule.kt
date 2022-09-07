package com.example.wantedpreonboarding.di

import com.example.wantedpreonboarding.data.repository.TopNewsRepositoryImpl
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
}
