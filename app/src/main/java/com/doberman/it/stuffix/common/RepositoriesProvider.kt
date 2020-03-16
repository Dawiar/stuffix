package com.doberman.it.stuffix.common

import com.doberman.it.stuffix.ui.homeScreen.itemsList.ItemsListRepository
import com.doberman.it.stuffix.ui.homeScreen.itemsList.ItemsListRepositoryImpl
import com.doberman.it.stuffix.ui.homeScreen.locationsList.LocationsListRepository
import com.doberman.it.stuffix.ui.homeScreen.locationsList.LocationsListRepositoryImpl
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

    @Provides
    fun itemsList(
        dao: DaoProvider
    ): ItemsListRepository = ItemsListRepositoryImpl(
        dao = dao.itemsList()
    )
}