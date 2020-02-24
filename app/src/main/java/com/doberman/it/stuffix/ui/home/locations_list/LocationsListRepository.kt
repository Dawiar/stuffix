package com.doberman.it.stuffix.ui.home.locations_list

import com.doberman.it.stuffix.common.locations.LocationModel

interface LocationsListRepository {
    suspend fun getLocationsList(): List<Location>

    interface Location {
        val id: Long
        val title: String
        val description: String
    }
}