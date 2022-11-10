package com.kareemdev.core.data.source.local

import com.kareemdev.core.data.source.local.entity.NewsEntity
import com.kareemdev.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val newsDao: NewsDao) {
    fun getAllTopHeadlines(): Flow<List<NewsEntity>> = newsDao.getAllTopHeadlines()
    fun getFavoriteTopHeadlines(): Flow<List<NewsEntity>> = newsDao.getFavoriteTopHeadlines()
    suspend fun insertTopHeadlines(newsList: List<NewsEntity>) =
        newsDao.insertTopHeadlines(newsList)

    fun setFavoriteNews(news: NewsEntity, newState: Boolean) {
        news.isFavorite = newState
        newsDao.updateTopHeadlines(news)
    }
}