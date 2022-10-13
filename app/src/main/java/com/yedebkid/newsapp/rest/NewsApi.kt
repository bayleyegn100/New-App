package com.yedebkid.newsapp.rest

import com.yedebkid.newsapp.model.DataX
import com.yedebkid.newsapp.model.TopStoriesNewsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(ALL_NEWS)
    suspend fun getAllNews(
        @Query("api_token") accesskey: String = ACCESS_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("limit") limit: Int = LIMIT
    ): Response<TopStoriesNewsItem>

    @GET(TOP_STORIES)
    suspend fun getTopStoriesNews(
        @Query("api_token") accesskey: String = ACCESS_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("limit") limit: Int = LIMIT
    ): Response<TopStoriesNewsItem>

    /**
     * Top Stories: https://api.thenewsapi.com/v1/news/top?api_token=7KpjzdkqpoavUd2jXc72kCP2OCREnDyDOb1kcL8F&locale=us&limit=3
        All news: https://api.thenewsapi.com/v1/news/all?api_token=7KpjzdkqpoavUd2jXc72kCP2OCREnDyDOb1kcL8F&language=en&limit=3
     */
    companion object {

        const val BASE_URL = "https://api.thenewsapi.com/v1/news/"
        private const val ACCESS_KEY = "7KpjzdkqpoavUd2jXc72kCP2OCREnDyDOb1kcL8F"
        private const val TOP_STORIES = "top"
        private const val ALL_NEWS = "all"
        private const val LANGUAGE = "en"
        private const val LIMIT = 5

    }
}
