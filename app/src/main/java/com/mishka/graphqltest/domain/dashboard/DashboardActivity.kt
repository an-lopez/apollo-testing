package com.mishka.graphqltest.domain.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_dashboard
        )

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_container_main
        ) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController = navHostFragment.navController, appBarConfiguration)

    }


}