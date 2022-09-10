package com.example.wantedpreonboarding.data.source.local.database

import com.example.wantedpreonboarding.data.source.local.dao.article.ArticleDao

/**
 * @Created by 김현국 2022/09/09
 */
interface AppDatabase {

    fun articleDao(): ArticleDao
}
