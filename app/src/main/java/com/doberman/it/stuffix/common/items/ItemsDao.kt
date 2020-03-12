package com.doberman.it.stuffix.common.items

import androidx.room.*

@Dao
abstract class ItemsDao {
    @Query("SELECT * FROM ItemsModel")
    abstract suspend fun all(): List<ItemsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(location: ItemsModel)

    @Entity
    class ItemsModel(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String,
        override val locationId: Long,
        override val address: String
    ) : Item
}
