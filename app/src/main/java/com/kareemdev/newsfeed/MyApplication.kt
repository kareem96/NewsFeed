package com.kareemdev.newsfeed

import android.app.Application
import com.kareemdev.core.di.databaseModule
import com.kareemdev.core.di.networkModule
import com.kareemdev.core.di.repositoryModule
import com.kareemdev.newsfeed.di.useCaseModule
import com.kareemdev.newsfeed.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}