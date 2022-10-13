package com.yedebkid.newsapp.model


import com.google.gson.annotations.SerializedName

data class NewsItem(
    @SerializedName("data")
    val data: List<DataX>?,
    @SerializedName("warnings")
    val warnings: List<String>?
)