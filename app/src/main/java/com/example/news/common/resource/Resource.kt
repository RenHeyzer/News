package com.example.news.common.resource

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(massage: String, data: T? = null) : Resource<T>(data = data, message = massage)
}