package com.doberman.it.stuffix.ui.home.locations_list

import com.doberman.it.stuffix.common.locations.LocationModel

interface LocationsListRepository {
    suspend fun getLocationsList(): List<Location>

    class Location (
        override val id: Long,
        override val title: String,
        override val description: String
    ) : LocationModel
}