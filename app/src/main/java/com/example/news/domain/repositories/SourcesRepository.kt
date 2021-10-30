package com.example.news.domain.repositories

import com.example.news.common.resource.Resource
import com.example.news.domain.models.Sources
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {
    fun getSources(page: Int): Flow<Resource<List<Sources>?>>
}