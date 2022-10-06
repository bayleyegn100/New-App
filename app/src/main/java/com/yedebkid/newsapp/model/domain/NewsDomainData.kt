package com.yedebkid.newsapp.model.domain

import com.yedebkid.newsapp.model.Data

data class NewsDomainData(
    val title: String,
    val description: String,
    val image: String,
    val date: String
)

fun List<Data?>?.mapToNewsDomainData(): List<NewsDomainData?>? =
    this?.map {
        NewsDomainData(
            title = it?.title ?: "",
            description = it?.description ?: "",
            image = it?.image ?: "",
            date = it?.publishedAt ?: "",
        )
    }


