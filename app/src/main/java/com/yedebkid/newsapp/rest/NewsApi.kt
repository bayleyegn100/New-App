package com.yedebkid.newsapp.rest

import com.yedebkid.newsapp.model.NewsData
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(LIVE_NEWS)
    suspend fun getLiveNews(
        @Query("access_key") accesskey: String = ACCESS_KEY,
        @Query("sources") language: String = LANGUAGE,
    ): retrofit2.Response<NewsData>

        //http://api.mediastack.com/v1/news?access_key=bdc64517773bbe5465f6e6a8c462148b&sources=en
    companion object {

        const val BASE_URL = "http://api.mediastack.com/v1/"
        private const val ACCESS_KEY = "bdc64517773bbe5465f6e6a8c462148b&sources=en"
        private const val LIVE_NEWS = "news"
        private const val LANGUAGE = "en"


    }
}