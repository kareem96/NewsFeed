package com.kareemdev.core.domain.usecase

import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.News
import com.kareemdev.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {
    override fun getAllTopHeadlines(): Flow<Resource<List<News>>> {
        return newsRepository.getTopHeadlines()
    }

    override fun getFavoriteNews(): Flow<List<News>> {
        return newsRepository.getFavoriteTopHeadlines()
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        return newsRepository.setFavoriteTopHeadlines(news, state)
    }
}