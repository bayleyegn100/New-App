package com.yedebkid.newsapp.model


import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)