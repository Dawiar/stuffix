package com.doberman.it.stuffix.ui.home.locationsList

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doberman.it.stuffix.common.Application
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.recyclerview.BindingRecyclerAdapter
import com.doberman.it.stuffix.databinding.FragmentLocationsListBinding

class LocationsListFragment : Fragment() {

    private val viewModel: LocationsListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                LocationsListViewModel(Application.repositories.locationsList()) as T
        }
    }

    private lateinit var dataBinding: FragmentLocationsListBinding

    private lateinit var adapter: BindingRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentLocationsListBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.locationsListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BindingRecyclerAdapter(viewLifecycleOwner)

        dataBinding.locationsListRecyclerView.adapter = adapter
        viewModel.selectableCells.observe(
            viewLifecycleOwner,
            Observer { cells ->
                adapter.setCell(cells)
            })


        dataBinding.locationsListFabAdd.setOnClickListener {
            val action =
                LocationsListFragmentDirections.actionNavigationLocationsToAddLocationFragment()
            this.findNavController().navigate(action)
        }
    }
}
