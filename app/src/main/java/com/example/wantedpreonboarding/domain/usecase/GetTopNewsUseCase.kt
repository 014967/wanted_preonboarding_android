package com.example.wantedpreonboarding.domain.usecase

import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.model.TopNews
import com.example.wantedpreonboarding.domain.repository.TopNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/07
 */
class GetTopNewsUseCase @Inject constructor(
    private val topNewsRepository: TopNewsRepository
) {
    operator fun invoke(): Flow<Results<List<TopNews>>> {
        return topNewsRepository.getTopNews()
    }
}
