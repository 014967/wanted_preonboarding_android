package com.example.wantedpreonboarding.di

import android.content.Context
import androidx.room.Room
import com.example.wantedpreonboarding.data.source.local.dao.article.ArticleDao
import com.example.wantedpreonboarding.data.source.local.database.AppDatabase
import com.example.wantedpreonboarding.data.source.local.database.AppDatabaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/09/09
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabaseImpl::class.java,
        AppDatabaseImpl.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao = appDatabase.articleDao()
}
