package com.doberman.it.stuffix.common.itemCategories

import androidx.room.*

@Dao
abstract class ItemCategoriesDao {
    @Query("SELECT * FROM ItemCategoriesModel")
    abstract suspend fun all(): List<ItemCategoriesModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(itemCategory: ItemCategoriesModel)

    @Entity
    class ItemCategoriesModel(
        @PrimaryKey override val id: Long,
        override val title: String
    ) : ItemCategory
}
