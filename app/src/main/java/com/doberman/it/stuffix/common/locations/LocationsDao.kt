package com.doberman.it.stuffix.common.locations

import androidx.room.*

@Dao
abstract class LocationsDao {
    @Query("SELECT * FROM LocationsModel")
    abstract suspend fun all(): List<LocationsModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(location: LocationsModel)

    @Query("DELETE FROM LocationsModel WHERE id in (:locationIDs)")
    abstract suspend fun delete(locationIDs: List<Long>)

    @Entity
    class LocationsModel(
        @PrimaryKey (autoGenerate = true) override val id: Long = 0,
        override val title: String,
        override val description: String,
        override val address: String
    ) : LocationModel
}
