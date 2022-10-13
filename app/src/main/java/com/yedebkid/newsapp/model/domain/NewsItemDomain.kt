package com.yedebkid.newsapp.model.domain

import com.yedebkid.newsapp.model.DataX

data class NewsItemDomain(
    val description: String,
    val image: String,
    val date: String,
    val title: String,
    val url: String
)

fun List<DataX?>?.mapToNewsItemDomain(): List<NewsItemDomain?>? =
    this?.map {
        NewsItemDomain(
            description = it?.description ?: "",
            image = it?.imageUrl ?: "",
            date = it?.publishedAt ?: "",
            title = it?.title ?: "",
            url = it?.url ?: ""
        )
    }
