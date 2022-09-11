package com.example.wantedpreonboarding.domain.usecase

import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.model.TopNews
import com.example.wantedpreonboarding.domain.repository.SavedNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/10
 */
class GetSavedTopNewsListUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
) {
    operator fun invoke(): Flow<Results<List<TopNews>>> {
        return savedNewsRepository.getSavedNewsList()
    }
}
