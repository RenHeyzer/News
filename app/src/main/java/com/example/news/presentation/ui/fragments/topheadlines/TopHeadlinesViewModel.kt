package com.example.news.presentation.ui.fragments.topheadlines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.base.BaseViewModel
import com.example.news.domain.models.Everything
import com.example.news.domain.usecases.GetTopHeadlinesUseCase
import com.example.news.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel
@Inject constructor(private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase) :
    BaseViewModel() {

    var page: Int = 2
    private val _newsState = MutableLiveData<UIState<List<Everything>?>>()
    val newsState: LiveData<UIState<List<Everything>?>> = _newsState

    init {
        getTopHeadlines(1)
    }

    fun getTopHeadlines(page: Int) {
        subscribeTo(_newsState) {
            getTopHeadlinesUseCase.execute(page)
        }
        Log.e("anime", "page:${page}")
    }
}