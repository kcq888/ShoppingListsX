package com.trekware.shoppinglistx.ui.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekware.shoppinglistx.data.ShoppingListItem
import com.trekware.shoppinglistx.data.ShoppingListDatabaseDao
import kotlinx.coroutines.launch

class ListViewModel(dataSource: ShoppingListDatabaseDao, currentCategory: Long) : ViewModel() {

    /**
     * Hold a reference to the Shopping List database via Shopping List Database Dao
     */
    private val database = dataSource

    private val shoppingListItem = MutableLiveData<ShoppingListItem?>()

    val lists = database.getAllItemList(currentCategory)

    init {
        // initializeLists()
    }

    private fun initializeLists() {
        viewModelScope.launch {
            val shoppingListItem = ShoppingListItem()
            shoppingListItem.category = 2
            shoppingListItem.itemCost = 3.0
            shoppingListItem.description = "Beef"
            shoppingListItem.itemQty = 2
            database.insert(shoppingListItem)
        }
    }
}