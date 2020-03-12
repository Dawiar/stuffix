package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.items.ItemsModel

class ItemsListRepositoryImpl(
    private val dao: ItemsDao
) : ItemsListRepository {
    override suspend fun addItem(item: ItemsModel) =
        dao.add(item as ItemsDao.ItemsModel)

    override suspend fun getItemsList(): List<ItemsModel> =
        dao.all()
}