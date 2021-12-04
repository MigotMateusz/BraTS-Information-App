package pl.mateuszmigot.brats_information.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import pl.mateuszmigot.brats_information.MyApp
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.databinding.ActivityDataBinding
import pl.mateuszmigot.brats_information.models.Team
import pl.mateuszmigot.brats_information.repositories.TeamsViewModel
import javax.inject.Inject

class DataActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDataBinding
    private lateinit var navController: NavController
    lateinit var teams: MutableList<Team>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbarWithNavigationDrawer()
        setNavigationViewListener()
        prepareBottomNavigation()
        teams = (application as MyApp).teamsViewModel.teams
        Log.i("DataActivity -> teams", teams.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_content_data)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupBinding() {
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun setupToolbarWithNavigationDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.data_drawer_layout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_content_data) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun prepareBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.data_bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.full_ranking_item -> {
                    navController.navigate(R.id.FullRankingFragment)
                    true
                }

                R.id.top_10_ranking_item -> {
                    navController.navigate(R.id.Top10Fragment)
                    true
                }
                R.id.my_models_item -> {
                    navController.navigate(R.id.MyModelsFragment)
                    true
                }
                else -> false
            }
        }
    }
}