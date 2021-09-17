package com.quyenln.qmeal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.quyenln.qmeal.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_QMeal)
        setContentView(R.layout.activity_main)
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        val navigationHostFragment =
            supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        val navController = navigationHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigation.isVisible = destination.id in mainFragment
        }

        bottomNavigation.apply {
            setupWithNavController(navController)
            selectedItemId = R.id.categoryFragment
            setOnItemReselectedListener {}
        }
    }

    companion object {
        private val mainFragment = listOf(
            R.id.categoryFragment,
            R.id.favoriteFragment,
            R.id.settingFragment,
            R.id.ingredientFragment
        )
    }

}
