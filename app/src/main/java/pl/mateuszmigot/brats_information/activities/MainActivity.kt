package pl.mateuszmigot.brats_information.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.databinding.ActivityMainBinding
import pl.mateuszmigot.brats_information.fragments.GeneralInfoFragment

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbarWithNavigationDrawer()
        setNavigationViewListener()
        prepareBottomNavigation()
        if (savedInstanceState == null) {
            changeWebViewInFragment(R.id.GeneralInfoFragment)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                goToSettingsActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun setupToolbarWithNavigationDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.main_drawer_layout)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun prepareBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_item_analysis -> {
                    changeWebViewInFragment(R.id.GeneralInfoFragment)
                    true
                }

                R.id.bottom_nav_item_data -> {
                    changeWebViewInFragment(R.id.DataGeneralInfoFragment)
                    true
                }
                R.id.bottom_nav_item_results -> {
                    changeWebViewInFragment(R.id.ResultsGeneralInfoFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun changeWebViewInFragment(page: Int) {
        //val bundle = bundleOf("page" to page)
        navController.navigate(page)
        /*supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<GeneralInfoFragment>(R.id.fragment_content_main, args = bundle)
        }*/
    }
}