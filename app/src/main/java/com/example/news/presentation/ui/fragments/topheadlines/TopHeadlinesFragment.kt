package com.example.news.presentation.ui.fragments.topheadlines

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentTopHeadlinesBinding
import com.example.news.domain.models.Everything
import com.example.news.presentation.state.UIState
import com.example.news.presentation.ui.activity.MainActivity
import com.example.news.presentation.ui.adapters.NewsAdapter
import com.example.news.common.extensions.scrollPagination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlinesFragment :
    BaseFragment<TopHeadlinesViewModel, FragmentTopHeadlinesBinding>(FragmentTopHeadlinesBinding::inflate) {

    override val viewModel: TopHeadlinesViewModel by viewModels()
    private val mAdapter = NewsAdapter()
    private lateinit var newsList: ArrayList<Everything>

    override fun setupRecyclers() {
        binding.apply {
            topheadlinesRecycler.layoutManager = LinearLayoutManager(requireContext())
            topheadlinesRecycler.adapter = mAdapter
            topheadlinesRecycler.scrollPagination { viewModel.getTopHeadlines(viewModel.page++) }
        }
    }

    override fun setupObservers() {
        subscribeToTopHeadlines()
    }

    override fun setupBottomNavItemReselect() {
        (requireActivity() as MainActivity).setOnBottomNavItemReselectListener {
            binding.topheadlinesRecycler.smoothScrollToPosition(0)
        }
    }

    private fun subscribeToTopHeadlines() {
        viewModel.newsState.observe(viewLifecycleOwner, {
            binding.loaderTopHeadlines.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                    mAdapter.showProgressBar()
                }
                is UIState.Error -> {
                    Log.e("anime", it.error)
                    mAdapter.hideProgressBar()
                }
                is UIState.Success -> {
                    mAdapter.hideProgressBar()
                    newsList = ArrayList(mAdapter.currentList)
                    it.data?.let { top ->
                        newsList.addAll(top)
                        mAdapter.submitList(newsList)
                    }

                }
            }
        })
    }
}