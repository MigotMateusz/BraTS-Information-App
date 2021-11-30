package pl.mateuszmigot.brats_information.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbarWithNavigationDrawer()
        setNavigationViewListener()
        prepareBottomNavigation()
        if (savedInstanceState == null) {
            changeWebViewInFragment("index.html")
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
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun prepareBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_item_analysis -> {
                    changeWebViewInFragment("index.html")
                    true
                }

                R.id.bottom_nav_item_data -> {
                    changeWebViewInFragment("data.html")
                    true
                }
                R.id.bottom_nav_item_results -> {
                    changeWebViewInFragment("results.html")
                    true
                }
                else -> false
            }
        }
    }

    private fun changeWebViewInFragment(page: String) {
        val bundle = bundleOf("page" to page)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<GeneralInfoFragment>(R.id.fragment_content_main, args = bundle)
        }
    }
}