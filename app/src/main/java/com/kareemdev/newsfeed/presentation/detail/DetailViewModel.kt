package com.kareemdev.newsfeed.presentation.detail

import androidx.lifecycle.ViewModel
import com.kareemdev.core.domain.model.News
import com.kareemdev.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    fun setFavoriteNews(news:News, newsStatus:Boolean) = newsUseCase.setFavoriteNews(news, newsStatus)
}