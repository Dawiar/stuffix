package com.doberman.it.stuffix.common.travels

import androidx.room.*
import com.doberman.it.stuffix.common.TravelStatusTypeConverter
import java.util.*

@Dao
abstract class TravelsDao {
    @Query("SELECT * FROM ItemsSetModel")
    abstract suspend fun all(): List<TravelsDaoModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(location: TravelsDaoModel)

    @Entity
    @TypeConverters(TravelStatusTypeConverter::class)
    class TravelsDaoModel(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val plannedDispatchDate: Date,
        override val plannedArriveDate: Date,
        override val dispatchDate: Date,
        override val arriveDate: Date,
        override val startPointId: Long,
        override val destinationPointId: Long,
        override val note: String,
        override val stuffSetId: Long,
        override val status: TravelStatus
    ) : TravelModel
}
