package com.example.assigaisle

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.assigaisle.databinding.ActivityMainBinding
import com.example.assigaisle.utils.gone
import com.example.assigaisle.utils.visible
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
        handleBottomNavVisibility()

        handleBottomNavDestinationsClicks()
        setUpBadges()
    }

    private fun setUpBadges() {

        val badgeMsg = binding.bottomNav.getOrCreateBadge(R.id.msg_dest)
        badgeMsg.isVisible = true
        badgeMsg.backgroundColor=resources.getColor(R.color.light_purple)
        badgeMsg.number = 50
        badgeMsg.maxCharacterCount = 3

        val badgeNotes = binding.bottomNav.getOrCreateBadge(R.id.notes_dest)
        badgeNotes.backgroundColor=resources.getColor(R.color.light_purple)
        badgeNotes.isVisible = true
        badgeNotes.number = 9
    }

    private fun handleBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.notesFragment) {
                binding.bottomNav.visible()
            } else {
                binding.bottomNav.gone()
            }
        }
    }


    //This function does nothing when menu items other than notes is clicked
    private fun handleBottomNavDestinationsClicks() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.discover_dest ->
                    return@setOnItemSelectedListener true

                R.id.notes_dest ->{
                    navController.navigate(R.id.notesFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.msg_dest ->
                    return@setOnItemSelectedListener true

                R.id.profile_dest ->
                    return@setOnItemSelectedListener true
            }
            false
        }
    }

    private fun makeStatusBarTransparent() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.white)
    }
}