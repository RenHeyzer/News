package com.example.news.data.network

import com.example.news.data.network.api.EverythingApiService
import com.example.news.data.network.api.SourcesApiService
import com.example.news.data.network.api.TopHeadlinesApiService
import com.example.news.data.network.interceptors.ApiKeyInterceptor
import com.example.news.common.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {

    private val provideOkhttpClient: OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val provideRetrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(provideOkhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun provideEverythingApiService(): EverythingApiService =
        provideRetrofit.create(EverythingApiService::class.java)

    fun provideTopHeadlinesApiService(): TopHeadlinesApiService =
        provideRetrofit.create(TopHeadlinesApiService::class.java)

    fun provideSourcesApiService(): SourcesApiService =
        provideRetrofit.create(SourcesApiService::class.java)
}