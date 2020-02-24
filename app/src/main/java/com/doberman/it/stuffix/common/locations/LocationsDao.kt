package com.doberman.it.stuffix.common.locations

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository

@Dao
abstract class LocationsDao {
    @Query("SELECT * FROM Model")
    abstract suspend fun all(): List<Model>

    @Entity
    class Model(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String
    ) : LocationsListRepository.Location
}
