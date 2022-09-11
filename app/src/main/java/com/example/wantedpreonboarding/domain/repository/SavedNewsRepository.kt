package com.example.wantedpreonboarding.domain.repository

import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.model.TopNews
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/09/10
 */
interface SavedNewsRepository {
    fun getSavedNewsList(): Flow<Results<List<TopNews>>>
}
