package com.trekware.shoppinglistx.ui.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trekware.shoppinglistx.data.ShoppingListDatabaseDao
import java.lang.IllegalArgumentException

class ListViewModelFactory(
    private val dataSource: ShoppingListDatabaseDao,
    private val currentCategory: Long) : ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(dataSource, currentCategory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}