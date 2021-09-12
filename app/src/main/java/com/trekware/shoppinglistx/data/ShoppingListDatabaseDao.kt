package com.trekware.shoppinglistx.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Define the data access method for using ShoppingList class with room
 */
@Dao
interface ShoppingListDatabaseDao {
    @Insert
    suspend fun insert(shoppingListItem: ShoppingListItem)

    @Update
    suspend fun update(shoppingListItem: ShoppingListItem)

    @Query("SELECT * FROM shopping_list_table Where listId = :key")
    suspend fun get(key: Long) : ShoppingListItem

    @Query( "DELETE FROM shopping_list_table")
    suspend fun clear()

    @Query("SELECT * FROM shopping_list_table Where list_category = :category ORDER BY listId DESC")
    fun getAllItemList(category: Long) :LiveData<List<ShoppingListItem>>
}