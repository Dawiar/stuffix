package com.doberman.it.stuffix.common.items

import androidx.room.*

@Dao
abstract class ItemsDao {
    @Query("SELECT * FROM ItemsDaoModel")
    abstract suspend fun all(): List<ItemsDaoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(location: ItemsDaoModel)

    @Entity
    class ItemsDaoModel(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String,
        override val locationId: Long
    ) : com.doberman.it.stuffix.common.items.ItemsModel
}