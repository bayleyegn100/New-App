package com.yedebkid.newsapp.view

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.yedebkid.newsapp.R
import com.yedebkid.newsapp.adapter.NewsAdapter
import com.yedebkid.newsapp.databinding.FragmentDetailsBinding
import com.yedebkid.newsapp.model.domain.NewsItemDomain
import com.yedebkid.newsapp.util.BaseFragment
import com.yedebkid.newsapp.util.ClicksHandler

class DetailsFragment : BaseFragment() {
private val binding by lazy {
    FragmentDetailsBinding.inflate(layoutInflater)
}
    private val newsAdapter by lazy {
        NewsAdapter { handler ->
            when(handler){
                is ClicksHandler.NewsUrlClicker -> {
                    Uri.parse(handler.url).also { uri ->
                        startActivity(Intent(ACTION_VIEW, uri))
                    }
                } else -> {

                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        newsAdapter
        populateNewsDetails(newsViewModel.newsItemDomainData)
        return binding.root
    }

    private fun populateNewsDetails(newsItemDomainData: NewsItemDomain? = null) {
        newsItemDomainData?.let {
            binding.detailNewsDate.text = it.date
            binding.detailNewsDescription.text = it.description
            binding.detailNewsUrl.text = it.url

            Picasso.get()
                .load(newsItemDomainData.image)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.detailNewsImage)
        } ?: Toast.makeText(requireContext(), "News is not available", Toast.LENGTH_LONG).show()
    }
}