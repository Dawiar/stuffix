package com.doberman.it.stuffix.ui.home.items_list

import android.app.Application
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
import com.doberman.it.stuffix.common.items.ItemsModel
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_items_list.*
import kotlinx.android.synthetic.main.fragment_locations_list.*

class ItemsListFragment : Fragment() {

    private lateinit var adapter: ItemsListRecyclerViewAdapter

    private val viewModel: ItemsListViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ItemsListViewModel(com.doberman.it.stuffix.common.Application.repositories.itemsList()) as T
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemsList_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ItemsListRecyclerViewAdapter()
        itemsList_recyclerView.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner, Observer<List<ItemsModel>>{ itemsList ->
            adapter.setItems(itemsList)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items_list, container, false)
    }

    companion object {
        fun newInstance() = ItemsListFragment()
    }
}
