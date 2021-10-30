package com.example.news.presentation.state

sealed class UIState<out T> {

    class Loading<T> : UIState<T>()
    class Error<T>(val error: String) : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
}
