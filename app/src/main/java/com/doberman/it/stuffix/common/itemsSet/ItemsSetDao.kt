package com.doberman.it.stuffix.common.itemsSet

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Dao
class ItemsSetDao {
    @Entity
    class ItemsSetDaoModel(
        @PrimaryKey override val id: Long,
        override val name: String,
        override val creationDate: Date
    ) : ItemsSet
}