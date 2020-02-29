package com.doberman.it.stuffix.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.locations.LocationsDao

@Database(
    entities = [LocationsDao.Model::class, ItemsDao.Model::class],
    version = 1
)
abstract class DaoProvider : RoomDatabase() {
    abstract fun locationsList(): LocationsDao
    abstract fun itemsList(): ItemsDao
}