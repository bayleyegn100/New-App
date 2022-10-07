package com.yedebkid.newsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yedebkid.newsapp.model.domain.NewsDomainData
import com.yedebkid.newsapp.rest.NewsRepository
import com.yedebkid.newsapp.util.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    var newsDomainData: NewsDomainData? = null
    private val _liveNews: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val liveNews:LiveData<UIState> get() = _liveNews

    init {
        getLiveNewsOnly()
    }

    private fun getLiveNewsOnly() {
        viewModelScope.launch(ioDispatcher){
            newsRepository.getAllLiveNews().collect() {
                _liveNews.postValue(it)

            }
        }
    }
}