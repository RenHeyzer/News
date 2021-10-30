package com.example.news.di

import com.example.news.data.network.api.EverythingApiService
import com.example.news.data.network.api.SourcesApiService
import com.example.news.data.network.api.TopHeadlinesApiService
import com.example.news.data.repositories.EverythingRepositoryImpl
import com.example.news.data.repositories.SourcesRepositoryImpl
import com.example.news.data.repositories.TopHeadlinesRepositoryImpl
import com.example.news.domain.repositories.EverythingRepository
import com.example.news.domain.repositories.SourcesRepository
import com.example.news.domain.repositories.TopHeadlinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideEverythingRepository(apiService: EverythingApiService): EverythingRepository =
        EverythingRepositoryImpl(apiService)

    @Provides
    fun provideTopHeadlinesRepository(apiService: TopHeadlinesApiService): TopHeadlinesRepository =
        TopHeadlinesRepositoryImpl(apiService)

    @Provides
    fun provideSourcesRepository(apiService: SourcesApiService): SourcesRepository =
        SourcesRepositoryImpl(apiService)
}