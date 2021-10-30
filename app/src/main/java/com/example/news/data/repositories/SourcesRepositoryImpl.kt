package com.example.news.data.repositories

import com.example.news.base.BaseRepository
import com.example.news.data.network.api.SourcesApiService
import com.example.news.data.network.dtos.toResponse
import com.example.news.data.network.dtos.toSources
import com.example.news.domain.repositories.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl
@Inject constructor(private val apiService: SourcesApiService) :
    BaseRepository(), SourcesRepository {

    override fun getSources(page: Int) = doRequest {
        apiService.getSources(page).toResponse().sources?.map {
            it.toSources()
        }
    }

}