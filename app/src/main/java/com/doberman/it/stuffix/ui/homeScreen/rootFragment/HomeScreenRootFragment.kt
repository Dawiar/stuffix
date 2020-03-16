package com.doberman.it.stuffix.ui.homeScreen.rootFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.doberman.it.stuffix.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeScreenRootFragment : Fragment() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        bottom_nav.setupWithNavController(navController)
        return inflater.inflate(R.layout.fragment_homescreen_root, container, false)
    }
}