package com.ysp.camep

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ysp.camep.databinding.MainActivityBinding

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.bind(findViewById<ViewGroup>(android.R.id.content)[0]) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_video)
//            R.id.navigation_video,
//            R.id.navigation_home,
//            R.id.navigation_dashboard,
//            R.id.navigation_notifications)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }
}