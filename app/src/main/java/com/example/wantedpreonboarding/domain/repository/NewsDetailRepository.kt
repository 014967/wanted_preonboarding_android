package com.example.wantedpreonboarding.domain.repository

import com.example.wantedpreonboarding.domain.model.TopNews
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/09/09
 */
interface NewsDetailRepository {

    fun saveNews(topNews: TopNews): Flow<Long>

    fun findSavedNews(title: String): Flow<Boolean>

    fun deleteNews(title: String): Flow<Int>

    fun getSavedNews(): Flow<TopNews>
}
