package com.kareemdev.newsfeed.di

import com.kareemdev.core.domain.usecase.NewsInteractor
import com.kareemdev.core.domain.usecase.NewsUseCase
import com.kareemdev.newsfeed.presentation.dashboard.DashboardViewModel
import com.kareemdev.newsfeed.presentation.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> {NewsInteractor(get())}
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}