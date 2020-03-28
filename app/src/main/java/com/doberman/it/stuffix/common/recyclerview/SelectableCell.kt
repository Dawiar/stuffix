package com.doberman.it.stuffix.common.recyclerview

import androidx.lifecycle.LifecycleOwner
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.databinding.FragmentLocationsItemBinding

class SelectableCell(val viewModel: SelectableSubViewModel) :
    BindableCell<FragmentLocationsItemBinding> {
    override val layoutId: Int
        get() = R.layout.fragment_locations_item

    override fun unbind(binding: FragmentLocationsItemBinding) {
        binding.unbind()
    }

    override fun bind(binding: FragmentLocationsItemBinding, lifecycleOwner: LifecycleOwner) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = lifecycleOwner
    }
}