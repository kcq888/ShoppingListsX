package com.trekware.shoppinglistx.ui.item

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.trekware.shoppinglistx.data.ShoppingListDatabase
import com.trekware.shoppinglistx.data.ShoppingListItem
import com.trekware.shoppinglistx.databinding.FragmentAddItemBinding
import com.trekware.shoppinglistx.ui.MainActivity
import com.trekware.shoppinglistx.ui.list.ListItemAdapter
import com.trekware.shoppinglistx.ui.list.ListViewModel
import com.trekware.shoppinglistx.ui.list.ListViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [AddItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddItemFragment : Fragment() {
    private lateinit var binding : FragmentAddItemBinding
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application

        // create an instance of the data source and viewmodel factory
        val dataSource = ShoppingListDatabase.getInstance(application).shoppingListDatabaseDao
        val viewModelFactory = ItemViewModelFactory(dataSource, application, binding)

        itemViewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)

        binding.okButton.setOnClickListener{ onItemOKButtonClicked() }
        binding.cancelButton.setOnClickListener{ onItemCancelButtonClicked() }

        return binding.root
    }

    private fun onItemCancelButtonClicked() {
        Log.d("Item", "Cancel button clicked")
        // navigate back to list
        findNavController().navigateUp()
    }

    private fun onItemOKButtonClicked() {
        Log.d("Item", "OK button clicked")
        val shoppingListItem = ShoppingListItem()
        val description = binding.itemDescription.editText?.text
        shoppingListItem.description = description.toString()
        val qty = binding.itemQty.editText?.text
        shoppingListItem.itemQty = qty.toString().toLong()
        val cost = binding.itemCost.editText?.text
        shoppingListItem.itemCost = cost.toString().toDouble()
        shoppingListItem.isItemFill = false
        val mainActivity : MainActivity = activity as MainActivity
        val itemCategory = mainActivity.currentItemCategory
        shoppingListItem.category = itemCategory
        itemViewModel.viewModelScope.launch {
            itemViewModel.insert(shoppingListItem)
        }
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}