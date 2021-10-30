package com.example.news.domain.repositories

import com.example.news.common.resource.Resource
import com.example.news.domain.models.Everything
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesRepository {

    fun getTopHeadlines(page: Int): Flow<Resource<List<Everything>?>>
}