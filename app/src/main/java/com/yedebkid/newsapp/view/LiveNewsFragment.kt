package com.yedebkid.newsapp.view

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yedebkid.newsapp.R
import com.yedebkid.newsapp.adapter.NewsAdapter
import com.yedebkid.newsapp.databinding.FragmentLiveNewsBinding
import com.yedebkid.newsapp.model.domain.NewsDomainData
import com.yedebkid.newsapp.util.BaseFragment
import com.yedebkid.newsapp.util.ClicksHandler
import com.yedebkid.newsapp.util.UIState

class LiveNewsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentLiveNewsBinding.inflate(layoutInflater)
    }

    private val newsAdapter by lazy {
        NewsAdapter {
            when(it){
                is ClicksHandler.NewsImageClicker -> {
                    Uri.parse(it.url).also {
                        startActivity(Intent(ACTION_VIEW, it))
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.livenewsRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
            adapter = newsAdapter
        }

        newsViewModel.liveNews.observe(viewLifecycleOwner) {
            when(it){
                is UIState.LOADING -> {
                    binding.livenewsRecyclerview.visibility = View.GONE
                    binding.newsLoadingSpinner.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.livenewsRecyclerview.visibility = View.VISIBLE
                    binding.newsLoadingSpinner.visibility = View.GONE
                    val newNews = it.data as List<NewsDomainData>
                    newsAdapter.updateNews(newNews)
                }
                is UIState.ERROR -> {
                    binding.livenewsRecyclerview.visibility = View.GONE
                    binding.newsLoadingSpinner.visibility = View.GONE
                }
            }
        }
        return binding.root
    }
}