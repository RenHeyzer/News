package com.example.news.data.network.api

import com.example.news.data.network.dtos.NewsResponseDto
import com.example.news.data.network.dtos.SourcesDto
import com.example.news.common.constants.Constants.BUSINESS
import com.example.news.common.constants.Constants.US
import retrofit2.http.GET
import retrofit2.http.Query

interface SourcesApiService {
    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query("page") page: Int,
        @Query("category") category: String = BUSINESS,
        @Query("country") country: String = US,
    ): NewsResponseDto<SourcesDto>
}