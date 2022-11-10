package com.kareemdev.core.data.source.remote.network

import com.kareemdev.core.data.source.remote.response.ListTopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String
    ): ListTopHeadlinesResponse
}