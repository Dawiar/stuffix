package com.doberman.it.stuffix.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.doberman.it.stuffix.BR
import com.doberman.it.stuffix.R

class SelectableCell(val viewModel: SelectableSubViewModel): BindableCell<ViewDataBinding> {
    override val layoutId: Int
        get() = R.layout.fragment_locations_item

    override fun bind(binding: ViewDataBinding, lifecycleOwner: LifecycleOwner) {
        binding.setVariable(BR.viewModel, viewModel )
    }

    override fun unbind(binding: ViewDataBinding) = Unit

}