package com.kareemdev.core.domain.usecase

import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllTopHeadlines():Flow<Resource<List<News>>>
    fun getFavoriteNews(): Flow<List<News>>
    fun setFavoriteNews(news: News, state: Boolean)
}