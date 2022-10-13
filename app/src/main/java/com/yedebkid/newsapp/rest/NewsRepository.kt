package com.yedebkid.newsapp.rest

import com.yedebkid.newsapp.model.domain.mapToNewsItemDomain
import com.yedebkid.newsapp.util.FailureResponseException
import com.yedebkid.newsapp.util.NullResponseException
import com.yedebkid.newsapp.util.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

interface NewsRepository {

    fun getAllLNewsHere(): Flow<UIState>
}
class NewsRepositoryImplementation @Inject constructor(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getAllLNewsHere(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        delay(2000)

        try {
            val response = newsApi.getAllNews()
            if (response.isSuccessful){
                 response.body()?.let {
                     emit(UIState.SUCCESS(it.mapToNewsItemDomain()))
                 } ?: throw NullResponseException("Response is Null.")
            } else {
                throw FailureResponseException(response.errorBody().toString())
            }

        } catch (e: Exception){
            emit(UIState.ERROR(e))
        }
    }

}