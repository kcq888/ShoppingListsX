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
    suspend fun insert(shoppingList: ShoppingList)

    @Update
    suspend fun update(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list_table Where listId = :key")
    suspend fun get(key: Long) : ShoppingList

    @Query( "DELETE FROM shopping_list_table")
    suspend fun clear()

    @Query("SELECT * FROM shopping_list_table ORDER BY listId DESC")
    fun getAllLists() :LiveData<List<ShoppingList>>
}