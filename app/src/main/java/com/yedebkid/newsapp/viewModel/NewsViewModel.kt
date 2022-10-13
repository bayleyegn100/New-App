package com.yedebkid.newsapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yedebkid.newsapp.model.domain.NewsItemDomain
import com.yedebkid.newsapp.rest.NewsRepository
import com.yedebkid.newsapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NewsViewModel"
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
   // To be used for click details of the news
    var newsItemDomainData: NewsItemDomain? = null

    private val _allNews: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val allNews:LiveData<UIState> get() = _allNews

    init {
        getLiveNewsOnly()
        getTopStoryNewsONly()
    }

    private fun getLiveNewsOnly() {
        viewModelScope.launch(ioDispatcher){
            newsRepository.getAllLNewsHere().collect() {
                _allNews.postValue(it)

            }
        }
    }
    private val _topStoriesNews: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val topStoriesNews:LiveData<UIState> get() = _topStoriesNews
    private  fun getTopStoryNewsONly(){
        viewModelScope.launch(ioDispatcher) {
            newsRepository.getTopStoriesNewsHere().collect(){
                _topStoriesNews.postValue(it)
            }
        }
    }
}