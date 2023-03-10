package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.model.BaseNewsModel
import com.example.newsproject.model.BreakingNewsTitleModel
import com.example.newsproject.model.TopStoriesNewsTitleModel
import com.example.newsproject.datasource.utils.ResultEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val breakingNewsUseCaseImpl: IBreakingNewsUseCase,
    private val topStoriesUseCase: ITopStoriesUseCase
) : ViewModel() {

    private val _newsStateFlow = MutableStateFlow<ResultEvent<BaseNewsModel>>(ResultEvent.Loading)
    val newsStateFlow: StateFlow<ResultEvent<BaseNewsModel>>
        get() = _newsStateFlow.asStateFlow()

    init {
        getTopStories()
    }

    fun getTopStories(isLoadingLocal: Boolean = false) {
        viewModelScope.launch {
            _newsStateFlow.emit(ResultEvent.Loading)
            _newsStateFlow.emit(breakingNewsUseCaseImpl.invoke(isLoadingLocal))
            _newsStateFlow.emit(topStoriesUseCase.invoke(isLoadingLocal))
        }
    }
}