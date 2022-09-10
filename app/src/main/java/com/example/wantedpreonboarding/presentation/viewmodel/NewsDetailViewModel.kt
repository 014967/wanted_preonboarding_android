package com.example.wantedpreonboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wantedpreonboarding.domain.usecase.DeleteSavedNewsUseCase
import com.example.wantedpreonboarding.domain.usecase.FindSavedNewsWithTitleUseCase
import com.example.wantedpreonboarding.domain.usecase.InsertNewsUseCase
import com.example.wantedpreonboarding.presentation.mapper.mappingTopNewsPresentationModelToDomainModel
import com.example.wantedpreonboarding.presentation.model.TopNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/09/09
 */

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val insertNewsUseCase: InsertNewsUseCase,
    private val findSavedNewsWithTitleUseCase: FindSavedNewsWithTitleUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModel() {

    private val _savedState = MutableStateFlow(false)
    val savedState = _savedState.asStateFlow()

    fun savedNews(news: TopNews) {
        // presentation to domain model
        val topNews = mappingTopNewsPresentationModelToDomainModel(topNews = news)
        viewModelScope.launch {
            insertNewsUseCase(topNews = topNews).collect {
                println(it)
            }
        }
    }

    fun checkSaved(title: String) {
        viewModelScope.launch {
            findSavedNewsWithTitleUseCase(title = title).collect { flag ->
                when (flag) {
                    false -> {
                    }
                    true -> {
                        _savedState.value = true
                    }
                }
            }
        }
    }

    fun deleteSavedNews(title: String) {
        viewModelScope.launch {
            deleteSavedNewsUseCase(title = title).collect {
                println(it)
            }
        }
    }
}
