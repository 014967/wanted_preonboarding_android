package com.example.wantedpreonboarding.domain.usecase

import com.example.wantedpreonboarding.domain.repository.NewsDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/09
 */
class DeleteSavedNewsUseCase @Inject constructor(
    private val newsDetailRepository: NewsDetailRepository
) {
    operator fun invoke(title: String): Flow<Int> {
        return newsDetailRepository.deleteNews(title = title)
    }
}
