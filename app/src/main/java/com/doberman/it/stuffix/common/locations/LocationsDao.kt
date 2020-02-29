package com.doberman.it.stuffix.common.locations

import androidx.room.*
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository

@Dao
abstract class LocationsDao {
    @Query("SELECT * FROM Model")
    abstract suspend fun all(): List<Model>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(location: Model)

    @Entity
    class Model(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String,
        override val address: String
    ) : LocationModel
}
