package com.doberman.it.stuffix.common

import android.app.Application
import androidx.room.Room

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
    }
}