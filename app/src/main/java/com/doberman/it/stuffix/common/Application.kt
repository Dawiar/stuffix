package com.doberman.it.stuffix.common

import android.app.Application
import androidx.room.Room
import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.items.ItemsModel
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.ui.home.items_list.ItemsListRepository
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository
import kotlinx.coroutines.*

class Application : Application() {
    companion object {
        lateinit var repositories: RepositoryComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        val room = Room
            .databaseBuilder(this, DaoProvider::class.java, "appDataBase")
            .build()

        repositories = DaggerRepositoryComponent
            .builder()
            .daoProvider(room)
            .repositoriesProvider(RepositoriesProvider())
            .build()

        // May be used to test the locations repository
        val repo : LocationsListRepository = repositories.locationsList()
        val rep1 : ItemsListRepository = repositories.itemsList()
        val locationsDeff : Deferred<List<LocationModel>> = GlobalScope.async {
            repo.addLocation(LocationsDao.Model(1, "Home", "Home, sweet home","Lenina st. 23"))
            repo.addLocation(LocationsDao.Model(2, "Obshaga", "Not so sweet, but still","Turgenevsky 44"))
            repo.getLocationsList()
        }

        val itemsDeff : Deferred<List<ItemsModel>> = GlobalScope.async {
            rep1.addItem(ItemsDao.Model(1,"Phone","Mobile",""))
            rep1.addItem(ItemsDao.Model(2,"Notebook","",""))
            rep1.getItemsList()
        }
        runBlocking {
            val locations = locationsDeff.await()
            val items = itemsDeff.await()
            print(locations)
        }
    }
}