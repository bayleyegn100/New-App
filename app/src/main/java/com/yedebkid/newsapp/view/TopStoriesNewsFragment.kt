package com.yedebkid.newsapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yedebkid.newsapp.R
import com.yedebkid.newsapp.adapter.NewsAdapter
import com.yedebkid.newsapp.databinding.FragmentTopStoriesNewsBinding
import com.yedebkid.newsapp.model.domain.NewsItemDomain
import com.yedebkid.newsapp.util.BaseFragment
import com.yedebkid.newsapp.util.ClicksHandler
import com.yedebkid.newsapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopStoriesNewsFragment : BaseFragment() {
    private val binding by lazy {
        FragmentTopStoriesNewsBinding.inflate(layoutInflater)
    }
    private val newsAdapter by lazy {
        NewsAdapter {
            when(it){
                is ClicksHandler.NewsUrlClicker -> {
                    Uri.parse(it.url).also {
                        startActivity(Intent(Intent.ACTION_VIEW, it))
                    }
                }
                is ClicksHandler.NewsDetailsClick -> {
                    newsViewModel.newsItemDomainData = it.news
                    findNavController().navigate(
                        R.id.action_TopStories_news_fragment_to_detailsFragment
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
        binding.topStoriesRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
            adapter = newsAdapter
        }

        newsViewModel.topStoriesNews.observe(viewLifecycleOwner) {
            when(it){
                is UIState.LOADING -> {
                    binding.topStoriesRecyclerview.visibility = View.GONE
                    binding.newsLoadingSpinner.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.topStoriesRecyclerview.visibility = View.VISIBLE
                    binding.newsLoadingSpinner.visibility = View.GONE
                    val TopStoriesNews = it.data as List<NewsItemDomain>
                    newsAdapter.updateNews(TopStoriesNews)
                }
                is UIState.ERROR -> {
                    binding.topStoriesRecyclerview.visibility = View.GONE
                    binding.newsLoadingSpinner.visibility = View.GONE
                }
            }
        }

        return binding.root
    }
}