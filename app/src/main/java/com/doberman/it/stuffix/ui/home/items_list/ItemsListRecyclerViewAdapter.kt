package com.doberman.it.stuffix.ui.home.items_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.common.items.ItemsModel

class ItemsListRecyclerViewAdapter :
    RecyclerView.Adapter<ItemsListRecyclerViewAdapter.ItemsViewHolder>() {

    private var items: List<ItemsModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_locations_item, parent, false)
        return ItemsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<ItemsModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.locationsList_item_title)
        val description: TextView = itemView.findViewById(R.id.locationsList_item_description)
        val address: TextView = itemView.findViewById(R.id.locationsList_item_address)

        fun bind(item: ItemsModel) {
            title.text = item.title
            description.text = item.description
            address.text = item.address

        }
    }
}