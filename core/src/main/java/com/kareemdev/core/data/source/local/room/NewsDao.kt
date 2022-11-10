package com.kareemdev.core.data.source.local.room

import androidx.room.*
import com.kareemdev.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllTopHeadlines(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where isFavorite = 1")
    fun getFavoriteTopHeadlines(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopHeadlines(news: List<NewsEntity>)

    @Update
    fun updateTopHeadlines(news: NewsEntity)
}