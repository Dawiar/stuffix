package com.doberman.it.stuffix.common.itemTransactions

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Dao
abstract class ItemTransactionsDao {
    @Entity
    class ItemTransactionModel(
        @PrimaryKey override val id: Long,
        override val itemId: Long,
        override val travelId: Long,
        override val qty: Int,
        override val isCompleted: Boolean
    ) : ItemTransaction
}