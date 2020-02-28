package com.doberman.it.stuffix.common.travels

import com.doberman.it.stuffix.common.locations.LocationModel
import java.util.*

interface TravelModel {
    val id: Long
    val title: String
    val plannedDispatchDate: Date
    val plannedArriveDate: Date
    val dispatchDate: Date
    val arriveDate: Date
    val startPoint: LocationModel
    val destinationPoint: LocationModel
    val note: String
    val stuffSetId: Long
    val status: Unit // TODO
}