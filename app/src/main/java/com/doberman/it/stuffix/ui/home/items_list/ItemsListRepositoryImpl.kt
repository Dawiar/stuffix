package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.items.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsListRepositoryImpl(
    private val dao: ItemsDao
) : ItemsListRepository {
    override suspend fun addItem(item: ItemsModel) {
        return withContext(Dispatchers.Default) {
            dao.add(item as ItemsDao.ItemsDaoModel)
        }
    }

    override suspend fun getItemsList(): List<ItemsModel> {
        return withContext(Dispatchers.Default) {
            dao.all()
        }
    }
}