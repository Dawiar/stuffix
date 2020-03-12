package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.common.items.ItemsModel

interface ItemsListRepository {
    suspend fun getItemsList(): List<ItemsModel>

    suspend fun addItem(item: ItemsModel)
}