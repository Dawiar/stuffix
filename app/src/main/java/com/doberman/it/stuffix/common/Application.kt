package com.doberman.it.stuffix.common

import android.app.Application
import androidx.room.Room
import com.doberman.it.stuffix.common.items.Item
import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.ui.home.itemsList.ItemsListRepository
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Application : Application() {
    companion object {
        lateinit var repositories: RepositoryComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        val room = Room
            .databaseBuilder(this, DaoProvider::class.java, "appDataBase")
            .fallbackToDestructiveMigration()
            .build()

        repositories = DaggerRepositoryComponent
            .builder()
            .daoProvider(room)
            .repositoriesProvider(RepositoriesProvider())
            .build()

        // May be used to test the locations repository
        val repo: LocationsListRepository = repositories.locationsList()
        val rep1: ItemsListRepository = repositories.itemsList()
        val locationsDeff: Deferred<List<LocationModel>> = GlobalScope.async {
            repo.addLocation(LocationsDao.LocationsModel(1, "abc", "def", "123"))
            repo.addLocation(LocationsDao.LocationsModel(2, "def", "ghj", "456"))
            repo.getLocationsList()
        }

        val itemsDeff: Deferred<List<Item>> = GlobalScope.async {
            rep1.addItem(ItemsDao.ItemsModel(1, "abc", "def", 1, "123"))
            rep1.addItem(ItemsDao.ItemsModel(2, "def", "ghj", 1, "456"))
            rep1.getItemsList()
        }
        runBlocking {
            val locations = locationsDeff.await()
            val items = itemsDeff.await()
            print(locations)
        }
    }
}