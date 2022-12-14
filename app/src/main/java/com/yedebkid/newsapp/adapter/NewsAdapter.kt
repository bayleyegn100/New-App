package com.yedebkid.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yedebkid.newsapp.R
import com.yedebkid.newsapp.databinding.NewsViewBinding
import com.yedebkid.newsapp.model.domain.NewsItemDomain
import com.yedebkid.newsapp.util.ClicksHandler

class NewsAdapter(
    private val dataSet: MutableList<NewsItemDomain> = mutableListOf(),
    private val clickHandler: (ClicksHandler) -> Unit

) : RecyclerView.Adapter<NewsViewHolder>() {

    fun updateNews(newNews: List<NewsItemDomain>) {
        dataSet.clear()
        dataSet.addAll(newNews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            NewsViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(dataSet[position], clickHandler)

    override fun getItemCount(): Int = dataSet.size

}

class NewsViewHolder(
    private val binding: NewsViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: NewsItemDomain, onClickHandler: (ClicksHandler) -> Unit) {
        binding.newsTitle.text = news.title
        binding.newsDate.text = news.date
//        binding.newsDescription.text = news.description
//        binding.newsUrl.apply {
//            text = news.url
//            setOnClickListener {
//                onClickHandler(ClicksHandler.NewsImageClicker(news.url))
//            }
//        }

        Picasso.get()
            .load(news.image)
            .placeholder(R.drawable.ic_baseline_image_search_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.newsImage)

        binding.root.setOnClickListener {
            onClickHandler(ClicksHandler.NewsDetailsClick(news))
        }

    }
}
