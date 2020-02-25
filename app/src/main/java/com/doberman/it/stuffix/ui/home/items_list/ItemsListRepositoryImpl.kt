package com.doberman.it.stuffix.ui.home.items_list

import com.doberman.it.stuffix.common.items.ItemsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsListRepositoryImpl(
    private val dao: ItemsDao
) : ItemsListRepository {
    override suspend fun addItem(item: ItemsListRepository.Item) {
        return withContext(Dispatchers.Default) {
            dao.add(item as ItemsDao.Model)
        }
    }

    override suspend fun getItemsList(): List<ItemsListRepository.Item> {
        return withContext(Dispatchers.Default) {
            dao.all()
        }
    }
}