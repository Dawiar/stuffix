package com.doberman.it.stuffix.common.itemCategories

import androidx.room.*

@Dao
abstract class ItemCategoriesDao {
    @Query("SELECT * FROM ItemCategoriesDaoModel")
    abstract suspend fun all(): List<ItemCategoriesDaoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(itemCategory: ItemCategoriesDaoModel)

    @Entity
    class ItemCategoriesDaoModel(
        @PrimaryKey override val id: Long,
        override val title: String
    ) : ItemCategory
}
