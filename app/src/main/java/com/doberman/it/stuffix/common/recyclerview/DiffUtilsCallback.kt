package com.doberman.it.stuffix.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

class DiffUtilsCallback(var oldList: List<BindableCell<ViewDataBinding>>,var newList: List<BindableCell<ViewDataBinding>>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].viewModel.location.id == newList[newItemPosition].viewModel.location.id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}