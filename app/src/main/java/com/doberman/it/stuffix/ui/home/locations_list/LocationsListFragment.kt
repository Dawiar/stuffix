package com.doberman.it.stuffix.ui.home.locations_list

import android.os.Bundle
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
import com.doberman.it.stuffix.databinding.FragmentLocationsListBinding
import com.doberman.it.stuffix.ui.home.items_list.ItemsListFragmentDirections

class LocationsListFragment : Fragment() {

    private val viewModel: LocationsListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                LocationsListViewModel(Application.repositories.locationsList()) as T
        }
    }

    private lateinit var dataBinding: FragmentLocationsListBinding

    private lateinit var adapter: LocationsListRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentLocationsListBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.locationsListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = LocationsListRecyclerViewAdapter()

        dataBinding.locationsListRecyclerView.adapter = adapter
        viewModel.locations.observe(
            viewLifecycleOwner,
            Observer<List<LocationModel>> { locationsList ->
                adapter.setLocations(locationsList)
            })


        dataBinding.locationsListFabAdd.setOnClickListener {
            val action = LocationsListFragmentDirections.actionNavigationLocationsToAddLocationFragment()
            this.findNavController().navigate(action)
        }

    }

    companion object {
        fun newInstance(): LocationsListFragment {
            return LocationsListFragment()
        }
    }
}
