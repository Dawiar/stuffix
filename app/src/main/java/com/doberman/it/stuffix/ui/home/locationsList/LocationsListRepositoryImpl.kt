package com.doberman.it.stuffix.ui.home.locationsList

import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.locations.LocationsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationsListRepositoryImpl(
    private val dao: LocationsDao
) : LocationsListRepository {
    override suspend fun addLocation(location: LocationModel) {
        return withContext(Dispatchers.Default) {
            dao.add(location as LocationsDao.LocationsModel)
        }
    }

    override suspend fun getLocationsList(): List<LocationModel> {
        return withContext(Dispatchers.Default) {
            dao.all()
        }
    }

    override suspend fun deleteLocation(locationIDs: List<Long>) =
            dao.delete(locationIDs)

}