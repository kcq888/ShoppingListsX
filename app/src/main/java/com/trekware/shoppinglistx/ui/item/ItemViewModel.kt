package com.trekware.shoppinglistx.ui.item

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trekware.shoppinglistx.data.ShoppingListDatabaseDao
import com.trekware.shoppinglistx.data.ShoppingListItem
import com.trekware.shoppinglistx.databinding.FragmentAddItemBinding
import com.trekware.shoppinglistx.databinding.FragmentListBinding

class ItemViewModel(dataSource: ShoppingListDatabaseDao, application: Application, binding: FragmentAddItemBinding) : ViewModel() {
    /**
     * Hold a reference to the Shopping List database via Shopping List Database Dao
     */
    private val database = dataSource
    private val itemBinding = binding
    private val shoppingListItem = MutableLiveData<ShoppingListItem?>()

    private fun onItemCancelButtonClicked() {
        // collect the item values and insert into the database
        val listItem = ShoppingListItem()
        listItem.description = itemBinding.itemDescription.toString()
        listItem.itemQty = itemBinding.itemQty.toString().toLong()
        listItem.itemCost = itemBinding.itemCost.toString().toDouble()
        listItem.isItemFill = false
    }

    suspend fun insert(shoppingListItem: ShoppingListItem) {
        database.insert(shoppingListItem)
    }
}