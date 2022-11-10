package com.kareemdev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTopHeadlinesResponse(
    @field:SerializedName("articles")
    val result:List<NewsResponse>
)