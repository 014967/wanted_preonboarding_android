package com.example.wantedpreonboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wantedpreonboarding.domain.model.Results
import com.example.wantedpreonboarding.domain.usecase.GetTopNewsUseCase
import com.example.wantedpreonboarding.presentation.mapper.mappingDomainModelToPresentationModel
import com.example.wantedpreonboarding.presentation.topnews.TopNewsUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/07
 */

@HiltViewModel
class TopNewsViewModel @Inject constructor(
    private val getTopNewsUseCase: GetTopNewsUseCase
) : ViewModel() {

    private val _topNews: MutableStateFlow<TopNewsUiStates> = MutableStateFlow(TopNewsUiStates.Loading)
    val topNews = _topNews.asStateFlow()

    fun getTopNews(category: String) {
        viewModelScope.launch {
            getTopNewsUseCase(category = category).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _topNews.value =
                            TopNewsUiStates.Success(mappingDomainModelToPresentationModel(result.value))
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }
}
