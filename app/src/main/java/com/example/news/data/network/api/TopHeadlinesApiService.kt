package com.example.news.data.network.api

import com.example.news.data.network.dtos.EverythingDto
import com.example.news.data.network.dtos.NewsResponseDto
import com.example.news.common.constants.Constants.US
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApiService {
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("page") page: Int,
        @Query("country") country: String = US,
    ): NewsResponseDto<EverythingDto>
}