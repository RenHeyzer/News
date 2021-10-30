package com.example.news.presentation.ui.fragments.everything

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.base.BaseViewModel
import com.example.news.domain.models.Everything
import com.example.news.domain.usecases.GetEverythingUseCase
import com.example.news.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel
@Inject constructor(private val getEverythingUseCase: GetEverythingUseCase) :
    BaseViewModel() {

    var page: Int = 2
    private val _newsState = MutableLiveData<UIState<List<Everything>?>>()
    val newsState: LiveData<UIState<List<Everything>?>> = _newsState

    init {
        getEverything(1)
    }

    fun getEverything(page: Int) {
        subscribeTo(_newsState) {
            getEverythingUseCase.execute(page)
        }
        Log.d("anime", "page:${page}")
    }
}