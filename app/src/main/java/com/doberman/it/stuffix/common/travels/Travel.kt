package com.doberman.it.stuffix.common.travels

import com.doberman.it.stuffix.common.locations.LocationModel
import java.util.*

interface Travel {
    val id: Long
    val title: String
    val plannedDispatchDate: Date
    val plannedArriveDate: Date
    val dispatchDate: Date
    val arriveDate: Date
    val startPointId: Long
    val destinationPointId: Long
    val note: String
    val stuffSetId: Long
    val status: TravelStatus
}

enum class TravelStatus(val code: Int){
    PLANNED(0),
    ACTIVE(1),
    FINISHED(2),
    CANCELED(3),
}