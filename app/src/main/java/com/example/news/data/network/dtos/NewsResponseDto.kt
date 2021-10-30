package com.example.news.data.network.dtos

import com.example.news.domain.models.NewsResponse
import com.google.gson.annotations.SerializedName

data class NewsResponseDto<T>(
    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: Int?,

    @SerializedName("articles")
    val articles: List<T>?,

    @SerializedName("sources")
    val sources: List<T>?,
)

fun <T> NewsResponseDto<T>.toResponse(): NewsResponse<T> {
    return NewsResponse(
        status,
        totalResults,
        articles,
        sources,
    )
}