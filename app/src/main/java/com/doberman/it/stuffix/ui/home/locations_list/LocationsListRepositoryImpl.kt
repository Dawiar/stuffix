package com.doberman.it.stuffix.ui.home.locations_list

import com.doberman.it.stuffix.common.locations.LocationsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationsListRepositoryImpl(
    private val dao: LocationsDao
) : LocationsListRepository {
    override suspend fun getLocationsList(): List<LocationsListRepository.Location> {
        val locations = withContext(Dispatchers.Default) {
            dao.all()
        }
        // return locations
    }
}