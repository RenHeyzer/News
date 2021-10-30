package com.example.news.data.repositories

import com.example.news.base.BaseRepository
import com.example.news.data.network.api.TopHeadlinesApiService
import com.example.news.data.network.dtos.toEverything
import com.example.news.data.network.dtos.toResponse
import com.example.news.domain.repositories.TopHeadlinesRepository
import javax.inject.Inject

class TopHeadlinesRepositoryImpl
@Inject constructor(private val apiService: TopHeadlinesApiService) :
    BaseRepository(), TopHeadlinesRepository {

    override fun getTopHeadlines(page: Int) = doRequest {
        apiService.getTopHeadlines(page).toResponse().articles?.map { it.toEverything() }
    }
}