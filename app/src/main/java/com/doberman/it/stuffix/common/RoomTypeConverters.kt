package com.doberman.it.stuffix.common

import androidx.room.TypeConverter
import com.doberman.it.stuffix.common.travels.TravelStatus
import java.util.*

class RoomDateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

class TravelStatusTypeConverter {
    @TypeConverter
    fun fromIntToTravelStatus(value: Int): TravelStatus? {
        return when (value) {
            0 -> TravelStatus.PLANNED
            1 -> TravelStatus.ACTIVE
            2 -> TravelStatus.FINISHED
            3 -> TravelStatus.CANCELED
            else -> null
        }
    }

    @TypeConverter
    fun travelStatusToInt(status: TravelStatus): Int {
        return status.code
    }
}