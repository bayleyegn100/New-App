package com.yedebkid.newsapp.model


import com.google.gson.annotations.SerializedName
import com.yedebkid.newsapp.model.Data
import com.yedebkid.newsapp.model.Meta

data class TopStoriesNewsItem(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("warnings")
    val warnings: List<String?>?
)