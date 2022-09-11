package com.example.wantedpreonboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.usecase.GetSavedTopNewsListUseCase
import com.example.wantedpreonboarding.presentation.mapper.mappingTopNewsModelDomainToPresentationModel
import com.example.wantedpreonboarding.presentation.saved.SavedUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/10
 */
@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedTopNewsListUseCase: GetSavedTopNewsListUseCase
) : ViewModel() {

    private val _savedTopNewsList: MutableStateFlow<SavedUiStates> = MutableStateFlow(SavedUiStates.Loading)
    val savedTopNewsList = _savedTopNewsList.asStateFlow()

    fun getSavedTopNewsList() {
        viewModelScope.launch {
            getSavedTopNewsListUseCase().collect { result ->
                when (result) {
                    is Results.Loading -> {
                        _savedTopNewsList.value = SavedUiStates.Loading
                    }
                    is Results.Success -> {
                        _savedTopNewsList.value = SavedUiStates.Success(
                            result.value.map { topNewsDomainModel ->
                                mappingTopNewsModelDomainToPresentationModel(topNewsDomainModel)
                            }
                        )
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }
}
