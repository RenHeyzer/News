package com.example.news.presentation.ui.fragments.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.base.BaseViewModel
import com.example.news.domain.models.Sources
import com.example.news.domain.usecases.GetSourcesUseCase
import com.example.news.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel
@Inject constructor(private val getSourcesUseCase: GetSourcesUseCase) :
    BaseViewModel() {

    var page = 2
    private val _sourcesState = MutableLiveData<UIState<List<Sources>?>>()
    val sourcesState: LiveData<UIState<List<Sources>?>> = _sourcesState

    init {
        getSources(1)
    }

    fun getSources(page: Int) {
        subscribeTo(_sourcesState) {
            getSourcesUseCase.execute(page)
        }
    }
}