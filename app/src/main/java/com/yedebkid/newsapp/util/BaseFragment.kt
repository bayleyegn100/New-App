package com.yedebkid.newsapp.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yedebkid.newsapp.viewModel.NewsViewModel

open class BaseFragment : Fragment() {
    protected val newsViewModel by lazy {
        ViewModelProvider(requireActivity())[NewsViewModel::class.java]
    }
}