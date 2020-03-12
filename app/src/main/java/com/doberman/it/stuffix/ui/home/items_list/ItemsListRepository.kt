package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.common.items.Item

interface ItemsListRepository {
    suspend fun getItemsList(): List<Item>

    suspend fun addItem(item: Item)
}