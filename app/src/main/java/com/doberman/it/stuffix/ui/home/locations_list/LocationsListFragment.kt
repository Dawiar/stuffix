package com.doberman.it.stuffix.ui.home.locations_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.doberman.it.stuffix.R

class LocationsListFragment : Fragment() {

    companion object {
        fun newInstance() = LocationsListFragment()
    }

    private lateinit var viewModel: LocationsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LocationsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
