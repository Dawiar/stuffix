package com.doberman.it.stuffix.common

import android.app.Application
import androidx.room.Room
import com.doberman.it.stuffix.common.locations.LocationsDao
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

        /*
        // May be used to test the locations repository
        val repo : LocationsListRepository = repositories.locationsList()
        val locationsDeff : Deferred<List<LocationsListRepository.Location>> = GlobalScope.async {
            repo.addLocation(LocationsDao.Model(1, "abc", "def"))
            repo.getLocationsList()
        }
        runBlocking {
            val locations = locationsDeff.await()
            print(locations)
        }*/
    }
}