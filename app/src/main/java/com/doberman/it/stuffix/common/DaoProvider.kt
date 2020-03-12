package com.doberman.it.stuffix.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.doberman.it.stuffix.common.itemCategories.ItemCategoriesDao
import com.doberman.it.stuffix.common.itemTransactions.ItemTransactionsDao
import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.common.itemsSet.ItemsSetDao
import com.doberman.it.stuffix.common.travels.TravelsDao

@Database(
    entities = [
        ItemCategoriesDao.ItemCategoriesModel::class,
        ItemsDao.ItemsModel::class,
        ItemTransactionsDao.ItemTransactionModel::class,
        LocationsDao.LocationsModel::class,
        ItemsSetDao.ItemsSetModel::class,
        TravelsDao.TravelsModel::class
    ],
    version = 1
)
@TypeConverters(RoomDateTypeConverter::class)
abstract class DaoProvider : RoomDatabase() {
    abstract fun locationsList(): LocationsDao
}