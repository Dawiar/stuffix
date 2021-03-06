package com.doberman.it.stuffix.ui.add.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.doberman.it.stuffix.common.Application
import com.doberman.it.stuffix.databinding.FragmentAddLocationBinding


class AddLocationFragment : Fragment(), AddLocationViewModel.Navigation {

    private val viewModel: AddLocationViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                AddLocationViewModel(Application.repositories.locationsList()) as T
        }
    }

    private lateinit var dataBinding: FragmentAddLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentAddLocationBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel

        viewModel.navigation.observe(viewLifecycleOwner) { navFunction ->
            navFunction?.invoke(this@AddLocationFragment)
        }
    }

    override fun showBlocker() = Unit
    override fun hideBlocker() = Unit
    override fun navigateToLocationList() {
        val action = AddLocationFragmentDirections.actionAddLocationFragmentToNavigationLocations()
        findNavController().navigate(action)
    }
}