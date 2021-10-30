package com.example.news.di

import com.example.news.data.network.RetrofitClient
import com.example.news.data.network.api.EverythingApiService
import com.example.news.data.network.api.SourcesApiService
import com.example.news.data.network.api.TopHeadlinesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    val retrofitClient: RetrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideEverythingApiService(): EverythingApiService =
        retrofitClient.provideEverythingApiService()

    @Singleton
    @Provides
    fun provideTopHeadlinesApiService(): TopHeadlinesApiService =
        retrofitClient.provideTopHeadlinesApiService()

    @Singleton
    @Provides
    fun provideSourcesApiService(): SourcesApiService =
        retrofitClient.provideSourcesApiService()
}