package com.trekware.shoppinglistx.ui.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.trekware.shoppinglistx.data.ShoppingListItem
import com.trekware.shoppinglistx.data.ShoppingListDatabaseDao

class ListViewModel(dataSource: ShoppingListDatabaseDao, application: Application) {

    /**
     * Hold a reference to the Shopping List database via Shopping List Database Dao
     */
    val database = dataSource

    private val shoppingList = MutableLiveData<ShoppingListItem?>()
    private val list = database.getAllItemList()


}