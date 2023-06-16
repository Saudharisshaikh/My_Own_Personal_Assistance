package com.example.myownpersonalassistance

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.*
import com.example.myownpersonalassistance.Fragments.AboutUsFragment
import com.example.myownpersonalassistance.Fragments.HomeFragment
import com.example.myownpersonalassistance.Fragments.PrivacyFragment
import com.example.myownpersonalassistance.Fragments.RatingFragment
import com.example.myownpersonalassistance.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.fragement_container_view) as NavHost

        navController = navHost.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment),
            activityMainBinding.drawerlayout
        )

        NavigationUI.setupWithNavController(
            activityMainBinding.toolbar,
            navController,
            appBarConfiguration
        )
        activityMainBinding.navigationView.setupWithNavController(navController)

        activityMainBinding.addMeeting.setOnClickListener {

            navController.navigate(R.id.action_homeFragment_to_addScheduleFragment)

        }

    }


    override fun onSupportNavigateUp(): Boolean {
        return   navController.navigateUp(appBarConfiguration) ||  super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }





}