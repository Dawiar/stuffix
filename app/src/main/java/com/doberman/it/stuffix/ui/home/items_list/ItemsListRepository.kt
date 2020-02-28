package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.common.items.ItemsModel
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository

interface ItemsListRepository {
    suspend fun getItemsList(): List<ItemsModel>

    suspend fun addItem(item: ItemsModel)
}