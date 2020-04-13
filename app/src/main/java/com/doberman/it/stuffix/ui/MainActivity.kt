package com.doberman.it.stuffix.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.RootNavigationGraphDirections
import com.doberman.it.stuffix.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var rootNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupNavigation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupNavigation() {
        rootNavController = findNavController(this, R.id.nav_host_fragment)

        val toolbar = binding.toolbar
        val bottomNav = binding.bottomNav
        val drawer = binding.drawerLayout

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_fragment_travels,
                R.id.navigation_fragment_items,
                R.id.navigation_fragment_locations
            ), drawer
        )

        toolbar.setupWithNavController(rootNavController, appBarConfiguration)
        bottomNav.setupWithNavController(rootNavController)

        rootNavController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id == R.id.navigation_fragment_sign_in
                || dest.id == R.id.navigation_fragment_sign_up
            ) {
                toolbar.visibility = View.GONE
                bottomNav.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
                bottomNav.visibility = View.VISIBLE
            }
        }

        binding.drawerNavView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id: Int = p0.itemId

        if (id == R.id.drawer_item_logout) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut()
            val action = RootNavigationGraphDirections.actionGlobalLogOut()
            rootNavController.navigate(action)
        } else if (id == R.id.drawer_item_settings) {

        }

        val drawer = binding.drawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
