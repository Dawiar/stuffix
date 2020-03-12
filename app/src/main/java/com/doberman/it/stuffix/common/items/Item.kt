package com.doberman.it.stuffix.common.items

interface Item {
    val id: Long
    val title: String
    val description: String
    val locationId: Long
    val address: String
}