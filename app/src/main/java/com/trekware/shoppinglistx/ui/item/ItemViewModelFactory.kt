package com.trekware.shoppinglistx.ui.item

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trekware.shoppinglistx.data.ShoppingListDatabaseDao
import com.trekware.shoppinglistx.databinding.FragmentAddItemBinding
import com.trekware.shoppinglistx.databinding.FragmentListBinding
import com.trekware.shoppinglistx.ui.list.ListViewModel
import java.lang.IllegalArgumentException

class ItemViewModelFactory(
    private val dataSource: ShoppingListDatabaseDao,
    private val application: Application,
    private val binding: FragmentAddItemBinding) : ViewModelProvider.Factory {
        override fun <T: ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
                return ItemViewModel(dataSource, application, binding) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }