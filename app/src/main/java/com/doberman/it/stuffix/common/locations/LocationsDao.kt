package com.doberman.it.stuffix.common.locations

import androidx.room.*

@Dao
abstract class LocationsDao {
    @Query("SELECT * FROM LocationsDaoModel")
    abstract suspend fun all(): List<LocationsDaoModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(location: LocationsDaoModel)

    @Entity
    class LocationsDaoModel(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String
    ) : LocationModel
}
