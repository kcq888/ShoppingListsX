package com.trekware.shoppinglistx.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.trekware.shoppinglistx.data.ShoppingListDatabase
import com.trekware.shoppinglistx.databinding.FragmentListBinding
import com.trekware.shoppinglistx.ui.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding : FragmentListBinding
    private val listItemAdapter = ListItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application

        // create an instance of the data source and viewmodel factory
        val dataSource = ShoppingListDatabase.getInstance(application).shoppingListDatabaseDao
        val currentCategory = (activity as MainActivity).currentItemCategory
        val viewModelFactory = ListViewModelFactory(dataSource, currentCategory)

        // get a reference to the view model
        val listViewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)

        binding.shoppingLists.adapter = listItemAdapter

        listViewModel.lists.observe(viewLifecycleOwner, Observer {
            it?.let {
                listItemAdapter.data = it
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}