package com.kareemdev.core.domain.repository

import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getTopHeadlines(): Flow<Resource<List<News>>>
    fun getFavoriteTopHeadlines(): Flow<List<News>>
    fun setFavoriteTopHeadlines(news: News, state: Boolean)
}