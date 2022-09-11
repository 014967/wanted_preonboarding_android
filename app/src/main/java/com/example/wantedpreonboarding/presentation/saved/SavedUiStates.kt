package com.example.wantedpreonboarding.presentation.saved

import com.example.wantedpreonboarding.presentation.model.TopNews

/**
 * @Created by 김현국 2022/09/11
 */

sealed class SavedUiStates {
    object Loading : SavedUiStates()

    data class Success(val news: List<TopNews>) : SavedUiStates()
}
