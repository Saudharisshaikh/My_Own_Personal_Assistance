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
import com.example.myownpersonalassistance.Fragments.AboutUsFragment
import com.example.myownpersonalassistance.Fragments.HomeFragment
import com.example.myownpersonalassistance.Fragments.PrivacyFragment
import com.example.myownpersonalassistance.Fragments.RatingFragment
import com.example.myownpersonalassistance.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var actionBarDrawerToggle :ActionBarDrawerToggle

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.fragement_container_view) as NavHost

        navController = navHost.navController


         actionBarDrawerToggle  = ActionBarDrawerToggle(this,activityMainBinding.drawerlayout,R.string.menu_open,R.string.menu_close)
        activityMainBinding.drawerlayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        setSupportActionBar(activityMainBinding.toolbar)
        setFragment(R.id.home,null)

        activityMainBinding.addMeeting.setOnClickListener {

            navController.navigate(R.id.action_homeFragment_to_addScheduleFragment)

        }

        activityMainBinding.navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.d("--item", "onCreate: "+item.itemId)
            setFragment(item.itemId, null)
            true
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun setFragment(@IdRes id: Int, bundle: Bundle?) {
        var bundle = bundle
        bundle = bundle ?: Bundle()
        var fragment: Fragment? = null
        when (id) {
            R.id.About -> {
                activityMainBinding.drawerlayout.closeDrawer(GravityCompat.START)
                fragment = AboutUsFragment()
                navController.navigate(R.id.action_homeFragment_to_aboutUsFragment)
            }
            R.id.home -> {
                activityMainBinding.drawerlayout.closeDrawer(GravityCompat.START)
                fragment = HomeFragment()

            }
            R.id.policy -> {
               activityMainBinding.drawerlayout.closeDrawer(GravityCompat.START)
                fragment = PrivacyFragment()
                navController.navigate(R.id.action_homeFragment_to_privacyFragment)
            }
            R.id.rating -> {
              activityMainBinding.drawerlayout.closeDrawer(GravityCompat.START)
                fragment = RatingFragment()
                navController.navigate(R.id.action_homeFragment_to_ratingFragment)
            }
            else -> {
               activityMainBinding.drawerlayout.closeDrawer(GravityCompat.START)
                fragment = HomeFragment()
            }
        }

    }


}