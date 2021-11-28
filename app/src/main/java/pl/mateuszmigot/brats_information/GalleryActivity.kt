package pl.mateuszmigot.brats_information

import android.graphics.Bitmap
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import pl.mateuszmigot.brats_information.databinding.ActivityGalleryBinding

class GalleryActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbarWithNavigationDrawer()
        prepareBottomNavigation()
        rawImages = (application as MyApp).imageRepository.rawImages
        segmentedImages = (application as MyApp).imageRepository.segmentedImages
        expertImages = (application as MyApp).imageRepository.expertImages
        viewPager = findViewById(R.id.rawSegImageViewPager)
        viewPagerAdapter = RawAndSegmentedImageViewPagerAdapter(
            this.applicationContext,
            rawImages,
            segmentedImages
        )
        viewPager.adapter = viewPagerAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupBinding() {
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.galleryToolbar)
    }

    private fun setupToolbarWithNavigationDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.gallery_drawer_layout)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        setNavigationViewListener()
    }

    private fun prepareBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.gallery_bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_item_model_and_expert -> {
                    viewPagerAdapter = RawAndSegmentedImageViewPagerAdapter(
                        this.applicationContext,
                        expertImages,
                        segmentedImages
                    )
                    viewPager.adapter = viewPagerAdapter
                    true
                }

                R.id.bottom_nav_item_raw_and_seg -> {
                    viewPagerAdapter = ModelAndExpertImageViewPagerAdapter(
                        this.applicationContext,
                        rawImages,
                        segmentedImages
                    )
                    viewPager.adapter = viewPagerAdapter
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        lateinit var rawImages: List<Bitmap>
        lateinit var segmentedImages: List<Bitmap>
        lateinit var expertImages: List<Bitmap>
    }
}