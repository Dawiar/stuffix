package com.doberman.it.stuffix.ui.homeScreen.itemsList

import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.items.Item

class ItemsListRepositoryImpl(
    private val dao: ItemsDao
) : ItemsListRepository {
    override suspend fun addItem(item: Item) =
        dao.add(item as ItemsDao.ItemsModel)

    override suspend fun getItemsList(): List<Item> =
        dao.all()
}