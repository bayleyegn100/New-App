package com.yedebkid.newsapp.view

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yedebkid.newsapp.R
import com.yedebkid.newsapp.adapter.NewsAdapter
import com.yedebkid.newsapp.databinding.FragmentAllNewsBinding
import com.yedebkid.newsapp.model.domain.NewsItemDomain
import com.yedebkid.newsapp.util.BaseFragment
import com.yedebkid.newsapp.util.ClicksHandler
import com.yedebkid.newsapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllNewsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentAllNewsBinding.inflate(layoutInflater)
    }

    private val newsAdapter by lazy {
        NewsAdapter {
            when(it){
                is ClicksHandler.NewsUrlClicker -> {
                    Uri.parse(it.url).also {
                        startActivity(Intent(ACTION_VIEW, it))
                    }
                }
                is ClicksHandler.NewsDetailsClick -> {
                    newsViewModel.newsItemDomainData = it.news
                    findNavController().navigate(
                        R.id.action_live_news_fragment_to_detailsFragment
                    )
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

        newsViewModel.allNews.observe(viewLifecycleOwner) {
            when(it){
                is UIState.LOADING -> {
                    binding.livenewsRecyclerview.visibility = View.GONE
                    binding.newsLoadingSpinner.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.livenewsRecyclerview.visibility = View.VISIBLE
                    binding.newsLoadingSpinner.visibility = View.GONE
                    val allNews = it.data as List<NewsItemDomain>
                    newsAdapter.updateNews(allNews)
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