package com.example.news.base

import com.example.news.common.resource.Resource
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioException: Exception) {
            emit(
                Resource.Error(
                    data = null,
                    massage = ioException.localizedMessage ?: "Error Occurred!"
                )
            )
        }
    }
}