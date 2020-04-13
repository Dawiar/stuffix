package com.doberman.it.stuffix.ui.home.locationsList

import com.doberman.it.stuffix.common.locations.LocationModel

interface LocationsListRepository {
    suspend fun getLocationsList(): List<LocationModel>

    suspend fun addLocation(location: LocationModel)

    suspend fun deleteLocation(locationIDs: List<Long>)
}