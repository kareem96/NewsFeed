package com.kareemdev.core.utils

import com.kareemdev.core.data.source.local.entity.NewsEntity
import com.kareemdev.core.data.source.remote.response.NewsResponse
import com.kareemdev.core.domain.model.News

object DataMapper {
    fun mapResponseToEntities(input: List<NewsResponse>): List<NewsEntity>{
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                author = it.author ?: "",
                title = it.title ?: "",
                description = it.description ?: "",
                url = it.url ?: "",
                urlToImage = it.urlToImage ?: "",
                publishedAt = it.publishedAt ?: "",
                content = it.content ?: "",
                isFavorite = false,
            )
            newsList.add(news)
        }
        return newsList
    }


    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> = input.map {
        News(
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
            isFavorite = it.isFavorite,
        )
    }

    fun mapDomainToEntity(input: News) = NewsEntity(
        author = input.author ?: "",
        title = input.title ?: "",
        description = input.description ?: "",
        url = input.url ?: "",
        urlToImage = input.urlToImage ?: "",
        publishedAt = input.publishedAt ?: "",
        content = input.content ?: "",
        isFavorite = input.isFavorite,
    )
}