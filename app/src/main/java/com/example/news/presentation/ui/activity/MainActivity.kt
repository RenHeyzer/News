package com.example.news.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavItemReselectListener: OnBottomNavigationItemReselect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navController = navHostFragment.navController

        with(binding.bottomNavigation) {
            setupWithNavController(navController)
            setOnItemReselectedListener {
                when (it.itemId) {
                    R.id.nav_everything,
                    R.id.nav_top_headlines,
                    R.id.nav_sources -> {
                        bottomNavItemReselectListener.onItemReselect()
                    }
                }
            }
        }
    }

    fun interface OnBottomNavigationItemReselect {
        fun onItemReselect()
    }

    fun setOnBottomNavItemReselectListener(bottomNavigationItemReselect: OnBottomNavigationItemReselect) {
        this.bottomNavItemReselectListener = bottomNavigationItemReselect
    }
}