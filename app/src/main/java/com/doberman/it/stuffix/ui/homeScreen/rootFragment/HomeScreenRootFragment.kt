package com.doberman.it.stuffix.ui.homeScreen.rootFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.doberman.it.stuffix.databinding.FragmentHomescreenRootBinding

class HomeScreenRootFragment : Fragment() {
    lateinit var homeNavController: NavController
    private lateinit var dataBinding: FragmentHomescreenRootBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHomescreenRootBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onStart() {
        super.onStart()
    }
}
