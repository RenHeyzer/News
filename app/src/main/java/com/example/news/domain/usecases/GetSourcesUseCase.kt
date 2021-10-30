package com.example.news.domain.usecases

import com.example.news.domain.repositories.SourcesRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val repository: SourcesRepository
) {
    fun execute(page: Int) = repository.getSources(page)
}