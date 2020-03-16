package com.doberman.it.stuffix.ui.homeScreen.itemsList

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
import com.doberman.it.stuffix.common.items.Item
import com.doberman.it.stuffix.databinding.FragmentItemsListBinding

class ItemsListFragment : Fragment() {

    private lateinit var adapter: ItemsListRecyclerViewAdapter

    private val viewModel: ItemsListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ItemsListViewModel(com.doberman.it.stuffix.common.Application.repositories.itemsList()) as T
        }
    }

    private lateinit var dataBinding: FragmentItemsListBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.itemsListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ItemsListRecyclerViewAdapter()
        dataBinding.itemsListRecyclerView.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner, Observer<List<Item>> { itemsList ->
            adapter.setItems(itemsList)
        })

        dataBinding.itemsListFabAdd.setOnClickListener {
            val action = ItemsListFragmentDirections.actionNavigationItemsToAddItemFragment()
            this.findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentItemsListBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    companion object {
        fun newInstance() = ItemsListFragment()
    }
}
