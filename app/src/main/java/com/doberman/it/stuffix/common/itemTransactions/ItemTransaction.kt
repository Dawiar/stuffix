package com.doberman.it.stuffix.common.itemTransactions

interface ItemTransaction {
    val id: Long
    val itemId: Long
    val travelId: Long
    val qty: Int
    val isCompleted: Boolean
}