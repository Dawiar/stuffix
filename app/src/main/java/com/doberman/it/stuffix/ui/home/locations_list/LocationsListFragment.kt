package com.doberman.it.stuffix.ui.home.locations_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager

import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.common.Application
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.ui.home.items_list.ItemsListViewModel
import kotlinx.android.synthetic.main.fragment_locations_list.*

class LocationsListFragment : Fragment() {

    private val viewModel: LocationsListViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                LocationsListViewModel(Application.repositories.locationsList()) as T
        }
    }
    private lateinit var adapter: LocationsListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        locationsList_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = LocationsListRecyclerViewAdapter()
        locationsList_recyclerView.adapter = adapter
        viewModel.locations.observe(viewLifecycleOwner, Observer<List<LocationModel>>{ locationsList ->
            adapter.setLocations(locationsList)
            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance(): LocationsListFragment {
            return LocationsListFragment()
        }
    }
}
