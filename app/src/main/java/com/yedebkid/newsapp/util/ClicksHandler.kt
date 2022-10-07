package com.yedebkid.newsapp.util

sealed class ClicksHandler {
    data class NewsImageClicker(val clickImage: String) : ClicksHandler()
}
