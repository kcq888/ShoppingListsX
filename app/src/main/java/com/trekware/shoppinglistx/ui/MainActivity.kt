package com.trekware.shoppinglistx.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.trekware.shoppinglistx.R
import com.trekware.shoppinglistx.data.ShoppingListDatabase
import com.trekware.shoppinglistx.data.ShoppingListDatabaseDao
import com.trekware.shoppinglistx.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),
    NavController.OnDestinationChangedListener {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private var dataSource: ShoppingListDatabaseDao? = null
    var currentItemCategory: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        dataSource = ShoppingListDatabase.getInstance(application).shoppingListDatabaseDao
        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setOnClickListener {
                navigateToAddItemView()
            }
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.groceries -> { onGroceryNavigation() }
                R.id.home_improvement -> { onHomeImprovementNavigation() }
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = application.resources.getText(R.string.grocery_list)

        binding.run {
            findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(this@MainActivity)
        }
    }

    private fun navigateToAddItemView() {
        //Toast.makeText(applicationContext, "FAB button cliced", Toast.LENGTH_SHORT).show()
        findNavController(R.id.nav_host_fragment).navigate(R.id.addItemFragment)
    }

    private fun onHomeImprovementNavigation() {
       // Toast.makeText(applicationContext, R.string.home_improvement, Toast.LENGTH_SHORT).show()
        supportActionBar?.title = application.resources.getText(R.string.home_improvement_list)
        currentItemCategory = 2
        findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
    }

    private fun onGroceryNavigation() {
        //Toast.makeText(applicationContext, R.string.groceries, Toast.LENGTH_SHORT).show()
        supportActionBar?.title = application.resources.getText(R.string.grocery_list)
        currentItemCategory = 1
        findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true

        when (item.itemId) {
            R.id.delete -> {
                Toast.makeText(applicationContext, R.string.delete, Toast.LENGTH_SHORT).show()
                lifecycleScope.launch() {
                    dataSource?.clear()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.listFragment -> {
                binding.fab.show()
            }
            R.id.addItemFragment -> {
                binding.fab.hide()
            }
        }
    }
}