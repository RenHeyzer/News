package com.example.news.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.example.news.R

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> Binding
) : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!
    protected abstract val viewModel: ViewModel
    private lateinit var mainNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = container?.let { inflate.invoke(inflater, it, false) }
        mainNavController =
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
        initialize()
        setupRequests()
        setupListener()
        setupObservers()
        return _binding?.root
    }


    open fun initialize() {
        setupRecyclers()
    }

    open fun setupRecyclers() {
    }

    open fun setupListener() {
        setupBottomNavItemReselect()
    }

    open fun setupRequests() {
    }

    open fun setupObservers() {
    }

    open fun setupBottomNavItemReselect() {

    }
}