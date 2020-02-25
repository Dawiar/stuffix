package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository

interface ItemsListRepository {
    suspend fun getItemsList(): List<Item>

    suspend fun addItem(item: Item)

    interface Item {
        val id: Long
        val title: String
        val description: String
    }
}