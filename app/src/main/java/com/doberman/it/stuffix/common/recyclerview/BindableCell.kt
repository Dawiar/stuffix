package com.doberman.it.stuffix.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

interface BindableCell<T : ViewDataBinding> {
    val layoutId: Int
    fun bind(binding: T, lifecycleOwner: LifecycleOwner)
    fun unbind(binding: T)
}
