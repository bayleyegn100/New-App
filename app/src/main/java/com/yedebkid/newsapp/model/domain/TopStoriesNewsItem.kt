package com.yedebkid.newsapp.model.domain


import com.google.gson.annotations.SerializedName

data class TopStoriesNewsItem(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("warnings")
    val warnings: List<String?>?
)