package com.doberman.it.stuffix.common

import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository
import dagger.Component

@Component(
    dependencies = [
        DaoProvider::class
    ],
    modules = [
        RepositoriesProvider::class
    ]
)
abstract class RepositoryComponent {
    abstract fun locationsList(): LocationsListRepository
}