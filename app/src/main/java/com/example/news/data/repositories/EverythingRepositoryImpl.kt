package com.example.news.data.repositories

import com.example.news.base.BaseRepository
import com.example.news.data.network.api.EverythingApiService
import com.example.news.data.network.dtos.toEverything
import com.example.news.data.network.dtos.toResponse
import com.example.news.domain.repositories.EverythingRepository
import javax.inject.Inject

class EverythingRepositoryImpl
@Inject constructor(
    private val apiService: EverythingApiService
) : BaseRepository(), EverythingRepository {

    override fun getEverything(page: Int) = doRequest {
        apiService.getEverything(page).toResponse().articles?.map { it.toEverything() }
    }
}