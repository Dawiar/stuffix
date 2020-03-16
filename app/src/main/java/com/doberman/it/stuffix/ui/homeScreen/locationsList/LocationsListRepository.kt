package com.doberman.it.stuffix.ui.homeScreen.locationsList

import com.doberman.it.stuffix.common.locations.LocationModel

interface LocationsListRepository {
    suspend fun getLocationsList(): List<LocationModel>

    suspend fun addLocation(location: LocationModel)
}