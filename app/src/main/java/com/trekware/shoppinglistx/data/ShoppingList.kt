package com.trekware.shoppinglistx.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_table")
data class ShoppingList (
    @PrimaryKey(autoGenerate = true)
    var listId : Long = 0L,

    @ColumnInfo(name = "list_category")
    var category : Long = 0L,

    @ColumnInfo(name = "list_description")
    var description : String = "",

    @ColumnInfo(name = "list_item_filled")
    var isItemFill : Boolean = false,

    @ColumnInfo(name = "list_item_qty")
    var itemQty : Long = 0L,

    @ColumnInfo(name = "list_item_cost")
    var itemCost : Double = 0.0
)