package com.kareemdev.newsfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationBarView
import com.kareemdev.newsfeed.databinding.ActivityMainBinding
import com.kareemdev.newsfeed.presentation.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = title

        setSupportActionBar(binding.toolbar)
        navigationChange(DashboardFragment())

        binding.bottomNav.setOnItemSelectedListener(navSelectedListen)

    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private val navSelectedListen = NavigationBarView.OnItemSelectedListener {
        when(it.itemId){
            R.id.dashboard -> {
                navigationChange(DashboardFragment())
                true
            }
            R.id.favorite -> {
                moveToFavoriteFragment()
                true
            }
            else -> false
        }
    }

    private fun moveToFavoriteFragment() {
        val fragment = instastateFragment()
        if(fragment != null){
            navigationChange(fragment)
        }
    }

    private fun instastateFragment(): Fragment? {
        return try {
            Class.forName("com.kareemdev.favorite.presentation.FavoriteFragment").newInstance() as Fragment
        }catch (e: Exception){
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

}