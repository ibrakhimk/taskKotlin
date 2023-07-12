package com.example.taskkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskkotlin.data.local.Pref
import com.example.taskkotlin.databinding.ActivityMainBinding
import com.example.taskkotlin.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPref()
        auth =FirebaseAuth.getInstance()
        initListener()
    }

    private fun initListener() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (auth.currentUser?.uid==null){
            navController.navigate(R.id.authFragment)
        }else if (!pref.isUserSeen()) {
            navController.navigate(R.id.onBordFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.proFileFragment, R.id.addTaskFragment)
        )
        val navigator = arrayListOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
            R.id.proFileFragment
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navView.isVisible = navigator.contains(destination.id)
            if (destination.id  == R.id.onBordFragment||destination.id  == R.id.addTaskFragment)
            { supportActionBar?.hide() } else supportActionBar?.show()
        }
        binding.navView.setupWithNavController(navController)
    }

    private fun initPref() {
        pref = Pref(this)
    }
}