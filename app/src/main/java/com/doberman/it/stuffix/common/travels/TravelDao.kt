package com.doberman.it.stuffix.common.travels

import androidx.room.*
import com.doberman.it.stuffix.common.locations.LocationModel
import java.util.*

@Dao
abstract class TravelDao {
    @Query("SELECT * FROM Model")
    abstract suspend fun all(): List<Model>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(location: Model)

    @Entity
    class Model(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val plannedDispatchDate: Date,
        override val plannedArriveDate: Date,
        override val dispatchDate: Date,
        override val arriveDate: Date,
        override val startPoint: LocationModel,
        override val destinationPoint: LocationModel,
        override val note: String,
        override val stuffSetId: Long,
        override val status: Unit
    ) : TravelModel
}
