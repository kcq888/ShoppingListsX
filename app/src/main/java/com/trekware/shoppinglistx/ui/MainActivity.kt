package com.trekware.shoppinglistx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.trekware.shoppinglistx.R
import com.trekware.shoppinglistx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

    private fun navigateToAddItemView() {
        //findNavController(R.id.host_fragment).navigate()
        //findNavController(R.id.addItemFragment).navigate(direction
        Toast.makeText(applicationContext, "FAB button cliced", Toast.LENGTH_SHORT).show()
        binding.fab.hide()
        findNavController(R.id.nav_host_fragment).navigate(R.id.addItemFragment)
    }

    private fun onHomeImprovementNavigation() {
        Toast.makeText(applicationContext, R.string.home_improvement, Toast.LENGTH_SHORT).show()
        supportActionBar?.title = "Home Improvement List"
        findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
        binding.fab.show()
    }

    private fun onGroceryNavigation() {
        Toast.makeText(applicationContext, R.string.groceries, Toast.LENGTH_SHORT).show()
        supportActionBar?.title = "Groceries List"
        findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
        binding.fab.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}