package com.example.news.domain.usecases

import com.example.news.domain.repositories.EverythingRepository
import javax.inject.Inject

class GetEverythingUseCase @Inject constructor(
    private val repository: EverythingRepository
) {

    fun execute(page: Int) = repository.getEverything(page)
}