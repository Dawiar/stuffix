package com.doberman.it.stuffix.common.items

import androidx.room.*
import com.doberman.it.stuffix.ui.home.items_list.ItemsListRepository

@Dao
abstract class ItemsDao {
    @Query("SELECT * FROM items")
    abstract suspend fun all(): List<Model>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(location: Model)

    @Entity(tableName = "items")
    class Model(
        @PrimaryKey override val id: Long,
        override val title: String,
        override val description: String
    ) : ItemsModel
}
