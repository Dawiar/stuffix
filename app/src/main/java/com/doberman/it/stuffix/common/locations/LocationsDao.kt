package com.doberman.it.stuffix.common.locations

import androidx.room.*

@Dao
abstract class LocationsDao {
    @Query("SELECT * FROM LocationsModel")
    abstract suspend fun all(): List<LocationsModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(location: LocationsModel)

    @Entity
    class LocationsModel(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String
    ) : LocationModel
}
