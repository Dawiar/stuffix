package com.doberman.it.stuffix.common

import com.doberman.it.stuffix.ui.home.itemsList.ItemsListRepository
import com.doberman.it.stuffix.ui.home.itemsList.ItemsListRepositoryImpl
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListRepository
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListRepositoryImpl
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