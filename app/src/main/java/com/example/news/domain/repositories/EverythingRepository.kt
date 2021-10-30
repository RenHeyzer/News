package com.example.news.domain.repositories

import com.example.news.common.resource.Resource
import com.example.news.domain.models.Everything
import kotlinx.coroutines.flow.Flow

interface EverythingRepository {

    fun getEverything(page: Int): Flow<Resource<List<Everything>?>>
}