package com.example.news.data.network.api

import com.example.news.data.network.dtos.EverythingDto
import com.example.news.data.network.dtos.NewsResponseDto
import com.example.news.common.constants.Constants.TECHNO
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingApiService {
    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("page") page: Int,
        @Query("domains") domains: String? = TECHNO,
    ): NewsResponseDto<EverythingDto>
}