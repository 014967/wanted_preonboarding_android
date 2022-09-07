package com.example.wantedpreonboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wantedpreonboarding.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment }
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        observeNavigationRoute()
    }

    private fun observeNavigationRoute() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_top_news, R.id.navigation_categories, R.id.navigation_saved))

        binding.bottomNavigation.setupWithNavController(navController = navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
