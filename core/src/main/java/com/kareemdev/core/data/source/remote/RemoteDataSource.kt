package com.kareemdev.core.data.source.remote

import android.util.Log
import com.kareemdev.core.data.source.remote.network.ApiResponse
import com.kareemdev.core.data.source.remote.network.ApiService
import com.kareemdev.core.data.source.remote.response.NewsResponse
import com.kareemdev.core.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {
    suspend fun getNewsTopHeadlines(): Flow<ApiResponse<List<NewsResponse>>> {
        return flow {
            try {
                val response = apiService.getTopHeadlines(Constants.COUNTRY,Constants.API_KEY)
                val dataArray = response.result
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Log.e("", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}