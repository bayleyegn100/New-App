package com.yedebkid.newsapp.rest

import com.yedebkid.newsapp.model.domain.mapToNewsDomainData
import com.yedebkid.newsapp.util.FailureResponseException
import com.yedebkid.newsapp.util.NullResponseException
import com.yedebkid.newsapp.util.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

interface NewsRepository {

    fun getAllLiveNews(): Flow<UIState>
}

class NewsRepositoryImplementation(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getAllLiveNews(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        delay(2000)

        try {
            val response = newsApi.getLiveNews()
            if (response.isSuccessful){
                 response.body()?.let {
                     emit(UIState.SUCCESS(it.data.mapToNewsDomainData()))
                 } ?: throw NullResponseException("Response is Null.")
            } else {
                throw FailureResponseException(response.errorBody().toString())
            }

        } catch (e: Exception){
            emit(UIState.ERROR(e))
        }
    }

}