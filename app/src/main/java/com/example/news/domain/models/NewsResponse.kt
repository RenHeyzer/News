package com.example.news.domain.models

data class NewsResponse<T>(
    val status: String?,

    val totalResults: Int?,

    val articles: List<T>?,

    val sources: List<T>?,
)
