package com.yedebkid.newsapp.util

import com.yedebkid.newsapp.model.domain.NewsItemDomain

sealed class ClicksHandler {
    data class NewsUrlClicker(val url: String) : ClicksHandler()
    data class NewsDetailsClick(val news: NewsItemDomain) : ClicksHandler()
}
