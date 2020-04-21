package com.doberman.it.stuffix.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import java.util.*

interface BindableCell<T : ViewDataBinding> {
    val viewModel: SelectableSubViewModel
    val layoutId: Int
    fun bind(binding: T, lifecycleOwner: LifecycleOwner)
    fun unbind(binding: T)
    override fun equals(other: Any?):Boolean
}
