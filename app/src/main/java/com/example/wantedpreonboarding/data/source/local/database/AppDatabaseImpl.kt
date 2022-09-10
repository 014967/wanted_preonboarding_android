package com.example.wantedpreonboarding.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wantedpreonboarding.data.source.local.entity.ArticleEntity
import com.example.wantedpreonboarding.data.source.local.entity.ArticleFtsEntity

/**
 * @Created by 김현국 2022/09/09
 */

@Database(
    entities = [ArticleEntity::class, ArticleFtsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabaseImpl : RoomDatabase(), AppDatabase {

    companion object {
        const val DB_NAME = "AppDatabase.db"
    }
}
