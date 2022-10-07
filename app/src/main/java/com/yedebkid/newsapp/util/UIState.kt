package com.yedebkid.newsapp.util

import java.lang.Exception

sealed class UIState {
    object LOADING: UIState()
    data class SUCCESS<out T>(val data: T): UIState()
    data class ERROR(val error: Exception): UIState()
}