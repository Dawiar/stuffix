package com.doberman.it.stuffix.common.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.doberman.it.stuffix.databinding.FragmentLocationsItemBindingImpl

class BindingRecyclerAdapter(
    val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<BindingRecyclerAdapter.BindableViewHolder>() {

    class BindableViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root)

     var cells = listOf<SelectableCell>()

    fun setCell(cells: List<SelectableCell>){
        this.cells = cells
        notifyDataSetChanged()
    }

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
        cells[position].bind( (holder.binding as FragmentLocationsItemBindingImpl) , lifecycleOwner)
    }
}
