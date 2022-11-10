package com.kareemdev.core.data

import com.kareemdev.core.data.source.local.LocalDataSource
import com.kareemdev.core.data.source.remote.RemoteDataSource
import com.kareemdev.core.data.source.remote.network.ApiResponse
import com.kareemdev.core.data.source.remote.response.NewsResponse
import com.kareemdev.core.domain.model.News
import com.kareemdev.core.domain.repository.INewsRepository
import com.kareemdev.core.utils.AppExecutors
import com.kareemdev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {
    override fun getTopHeadlines(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val newsList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertTopHeadlines(newsList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> {
                return remoteDataSource.getNewsTopHeadlines()
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllTopHeadlines().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }
        }.asFlow()

    override fun getFavoriteTopHeadlines(): Flow<List<News>> {
        return localDataSource.getFavoriteTopHeadlines().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteTopHeadlines(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute{localDataSource.setFavoriteNews(newsEntity, state)}
    }


}