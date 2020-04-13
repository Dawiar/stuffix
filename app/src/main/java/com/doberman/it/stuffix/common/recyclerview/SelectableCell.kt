package com.doberman.it.stuffix.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.databinding.FragmentLocationsItemBinding
import com.doberman.it.stuffix.databinding.FragmentLocationsItemBindingImpl

class SelectableCell(override val viewModel: SelectableSubViewModel) :
    BindableCell<FragmentLocationsItemBinding> {
    override val layoutId: Int = R.layout.fragment_locations_item

    override fun unbind(binding: FragmentLocationsItemBinding) {
        binding.unbind()
        binding.lifecycleOwner = null
    }

    override fun bind(binding: FragmentLocationsItemBinding, lifecycleOwner: LifecycleOwner) {
        binding.viewModel = viewModel
    }

    override fun equals(other: Any?): Boolean {
        return if (other is SelectableCell){
            (this.viewModel.location.title == other.viewModel.location.title
                    && this.viewModel.location.address == other.viewModel.location.address
                    && this.viewModel.location.description == other.viewModel.location.description)
        } else false
    }
}