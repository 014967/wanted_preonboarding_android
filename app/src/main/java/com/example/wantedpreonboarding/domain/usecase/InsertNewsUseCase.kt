package com.example.wantedpreonboarding.domain.usecase

import com.example.wantedpreonboarding.domain.model.TopNews
import com.example.wantedpreonboarding.domain.repository.NewsDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/09
 */
class InsertNewsUseCase @Inject constructor(
    private val newsDetailRepository: NewsDetailRepository
) {
    operator fun invoke(topNews: TopNews): Flow<Long> {
        return newsDetailRepository.saveNews(topNews = topNews)
    }
}
