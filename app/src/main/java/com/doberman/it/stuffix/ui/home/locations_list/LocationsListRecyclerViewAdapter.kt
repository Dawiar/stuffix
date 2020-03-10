package com.doberman.it.stuffix.ui.home.locations_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.common.locations.LocationModel
import kotlinx.android.synthetic.main.fragment_locations_item.*

class LocationsListRecyclerViewAdapter: RecyclerView.Adapter<LocationsListRecyclerViewAdapter.LocationsViewHolder>() {

    private var locations: List<LocationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_locations_item, parent,false)
        return LocationsViewHolder(view)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    fun setLocations(loc: List<LocationModel>){
        locations = loc
        notifyDataSetChanged()
    }

    class LocationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.locationsList_item_title)
        val description: TextView = itemView.findViewById(R.id.locationsList_item_description)
        val adress: TextView = itemView.findViewById(R.id.locationsList_item_address)

        fun bind(item: LocationModel){
            title.text = item.title
            description.text = item.description
            adress.text = item.address

        }
    }
}