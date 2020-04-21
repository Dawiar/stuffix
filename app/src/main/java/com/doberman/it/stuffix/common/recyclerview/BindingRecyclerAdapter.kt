package com.doberman.it.stuffix.common.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerAdapter(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<BindingRecyclerAdapter.BindableViewHolder>() {

    class BindableViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var cells = listOf<BindableCell<ViewDataBinding>>()


    fun setData(cells: List<BindableCell<ViewDataBinding>>) {
        this.cells = cells
    }

    fun getData(): List<BindableCell<ViewDataBinding>> {
        return cells
    }

/*
    override fun onViewDetachedFromWindow(holder: BindableViewHolder) {
        cells[holder.adapterPosition].unbind(holder.binding)
    }
*/

    override fun getItemViewType(position: Int): Int = cells[position].layoutId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {

        return BindableViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cells.size

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner

        (cells[position]).bind(holder.binding, lifecycleOwner)
    }
}
