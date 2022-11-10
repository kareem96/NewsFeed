package com.kareemdev.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kareemdev.core.domain.usecase.NewsUseCase

class FavoriteViewModel (newsUseCase: NewsUseCase): ViewModel(){
    val favoriteNews = newsUseCase.getFavoriteNews().asLiveData()
}