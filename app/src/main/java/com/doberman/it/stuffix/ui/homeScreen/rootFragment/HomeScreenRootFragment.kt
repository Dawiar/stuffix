package com.doberman.it.stuffix.ui.homeScreen.rootFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.databinding.FragmentHomescreenRootBinding
import com.doberman.it.stuffix.databinding.FragmentItemsListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreenRootFragment : Fragment() {
    lateinit var navController: NavController
    private lateinit var dataBinding: FragmentHomescreenRootBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHomescreenRootBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        dataBinding.bottomNav.setupWithNavController(navController)
    }
}
