package com.kareemdev.newsfeed.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kareemdev.core.domain.usecase.NewsUseCase

class DashboardViewModel(newsUseCase: NewsUseCase): ViewModel(){
    val news = newsUseCase.getAllTopHeadlines().asLiveData()
}