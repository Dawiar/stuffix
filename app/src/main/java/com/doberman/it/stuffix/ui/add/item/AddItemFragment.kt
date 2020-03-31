package com.doberman.it.stuffix.ui.add.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.doberman.it.stuffix.common.Application
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import com.doberman.it.stuffix.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {


    private val viewModel: AddItemViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                AddItemViewModel(
                    Application.repositories.itemsList(),
                    Application.repositories.locationsList()
                ) as T
        }
    }

    private lateinit var dataBinding: FragmentAddItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentAddItemBinding.inflate(inflater, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel

        dataBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel
                    .locations
                    .value
                    ?.getOrNull(position)
                    ?.let { viewModel.selectedLocation = it }
            }
        }

        viewModel.locations.observe(viewLifecycleOwner, Observer {
            displayLocations(it)
        })

        viewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            when (command) {
                is NavigationCommand.To ->
                    findNavController().navigate(command.directions)
            }
        })


    }


    private fun displayLocations(locations: List<LocationModel>?) {
        locations ?: return

        dataBinding.spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            locations.map { it.title }
        )

        locations.indexOf(viewModel.selectedLocation)
            .takeIf { it > -1 }
            ?.let { dataBinding.spinner.setSelection(it) }

    }
}