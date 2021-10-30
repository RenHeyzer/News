package com.example.news.presentation.ui.fragments.everything

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentEverythingBinding
import com.example.news.domain.models.Everything
import com.example.news.presentation.state.UIState
import com.example.news.presentation.ui.activity.MainActivity
import com.example.news.presentation.ui.adapters.NewsAdapter
import com.example.news.common.extensions.scrollPagination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EverythingFragment :
    BaseFragment<EverythingViewModel, FragmentEverythingBinding>(FragmentEverythingBinding::inflate) {

    override val viewModel: EverythingViewModel by viewModels()
    private val mAdapter = NewsAdapter()
    private lateinit var newsList: ArrayList<Everything>

    override fun setupRecyclers() {
        binding.apply {
            val linear = LinearLayoutManager(requireContext())
            everythingRecycler.layoutManager = linear
            everythingRecycler.adapter = mAdapter
            everythingRecycler.scrollPagination { viewModel.getEverything(viewModel.page++) }
        }
    }

    override fun setupObservers() {
        subscribeToEverything()
    }

    override fun setupBottomNavItemReselect() {
        (requireActivity() as MainActivity).setOnBottomNavItemReselectListener {
            binding.everythingRecycler.smoothScrollToPosition(0)
        }
    }

    private fun subscribeToEverything() {
        viewModel.newsState.observe(viewLifecycleOwner, {
            binding.loaderEverything.isVisible = it is UIState.Loading

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
                    it.data?.let { it1 ->
                        newsList.addAll(it1)
                        mAdapter.submitList(newsList)
                    }
                }
            }
        })
    }
}
