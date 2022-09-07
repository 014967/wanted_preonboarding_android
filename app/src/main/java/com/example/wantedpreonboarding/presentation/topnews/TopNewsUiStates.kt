package com.example.wantedpreonboarding.presentation.topnews

import com.example.wantedpreonboarding.presentation.model.TopNews

/**
 * @Created by 김현국 2022/09/07
 */
sealed class TopNewsUiStates {
    object Loading : TopNewsUiStates()

    data class Success(val news: List<TopNews>) : TopNewsUiStates()
}
