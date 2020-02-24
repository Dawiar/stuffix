package com.doberman.it.stuffix.common

import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoriesProvider {
    @Provides
    fun locationsList(
        dao: DaoProvider
    ): LocationsListRepository = LocationsListRepositoryImpl(
        dao = dao.locationsList()
    )
}