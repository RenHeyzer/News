package com.example.news.domain.usecases

import com.example.news.domain.repositories.TopHeadlinesRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: TopHeadlinesRepository
) {
    fun execute(page: Int) = repository.getTopHeadlines(page)
}