package com.doberman.it.stuffix.ui.home.locationsList

import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.doberman.it.stuffix.common.Application
import com.doberman.it.stuffix.common.recyclerview.*
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import com.doberman.it.stuffix.databinding.FragmentLocationsListBinding
import kotlinx.android.synthetic.main.activity_main.*

class LocationsListFragment : Fragment() {

    private val viewModel: LocationsListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                LocationsListViewModel(Application.repositories.locationsList()) as T
        }
    }

    private lateinit var dataBinding: FragmentLocationsListBinding
    private var actionMode: ActionMode? = null
    var callback: OnBackPressedCallback? = null
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

        callback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                viewModel.disableMultiselection()
            }
        }

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.locationsListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BindingRecyclerAdapter(viewLifecycleOwner)

        dataBinding.locationsListRecyclerView.adapter = adapter

        viewModel.locations.observe(
            viewLifecycleOwner,
            Observer { locations ->
                var cells = locations.map {
                    SelectableCell(
                        SelectableSubViewModel(
                            it,
                            viewModel
                        )
                    )
                }

                if (adapter.getData().isNotEmpty()) {
                    var result = DiffUtil.calculateDiff(
                        DiffUtilsCallback(
                            adapter.getData(),
                            cells as List<BindableCell<ViewDataBinding>>
                        )
                    )
                    adapter.setData(cells as List<BindableCell<ViewDataBinding>>)
                    result.dispatchUpdatesTo(adapter)
                } else {
                    adapter.setData(cells as List<BindableCell<ViewDataBinding>>)
                    adapter.notifyDataSetChanged()
                }
            })



        viewModel.isMultipleSelection.observe(viewLifecycleOwner) {
            if (it) {
                callback?.isEnabled = true
                actionMode = activity?.toolbar?.startActionMode(ActionModeCallback(viewModel, adapter.getData()))
            } else callback?.isEnabled = false
        }

        viewModel.updateHeader.observe(viewLifecycleOwner) {
            if (it == false) {
                actionMode?.finish()
            } else {
                val selectedCells = adapter.getData().filter { cell -> cell.viewModel.isSelected.value == true }
                actionMode?.title = "${selectedCells.size} Selected"
            }
        }

        viewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            when (command) {
                is NavigationCommand.To ->
                    findNavController().navigate(command.directions)
            }
        })
    }
}
