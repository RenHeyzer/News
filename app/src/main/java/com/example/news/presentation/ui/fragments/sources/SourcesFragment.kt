package com.example.news.presentation.ui.fragments.sources

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentSourcesBinding
import com.example.news.domain.models.Sources
import com.example.news.presentation.state.UIState
import com.example.news.presentation.ui.activity.MainActivity
import com.example.news.presentation.ui.adapters.SourcesAdapter
import com.example.news.utils.scrollPagination
import com.example.news.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment :
    BaseFragment<SourcesViewModel, FragmentSourcesBinding>(FragmentSourcesBinding::inflate) {

    override val viewModel: SourcesViewModel by viewModels()
    private val mAdapter = SourcesAdapter()
    private lateinit var sourcesList: ArrayList<Sources>

    override fun setupRecyclers() {
        binding.apply {
            sourcesRecycler.layoutManager = LinearLayoutManager(requireContext())
            sourcesRecycler.adapter = mAdapter
            sourcesRecycler.scrollPagination { viewModel.getSources(viewModel.page++) }
        }
    }

    override fun setupObservers() {
        subscribeToSources()
    }

    override fun setupBottomNavItemReselect() {
        (requireActivity() as MainActivity).setOnBottomNavItemReselectListener {
            binding.sourcesRecycler.smoothScrollToPosition(0)
        }
    }

    private fun subscribeToSources() {
        viewModel.sourcesState.observe(viewLifecycleOwner, {
            binding.loaderSources.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                    mAdapter.showProgressBar()
                }
                is UIState.Error -> {
                    requireActivity().toast(it.error)
                    mAdapter.hideProgressBar()
                }
                is UIState.Success -> {
                    mAdapter.hideProgressBar()
                    sourcesList = ArrayList(mAdapter.currentList)
                    it.data?.let { sources ->
                        sourcesList.addAll(sources)
                        mAdapter.submitList(sourcesList)
                    }
                }
            }
        })
    }
}