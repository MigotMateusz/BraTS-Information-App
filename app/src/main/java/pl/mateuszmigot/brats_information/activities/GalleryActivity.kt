package pl.mateuszmigot.brats_information.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import pl.mateuszmigot.brats_information.MyApp
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.adapters.ModelAndExpertImageViewPagerAdapter
import pl.mateuszmigot.brats_information.adapters.RawAndSegmentedImageViewPagerAdapter
import pl.mateuszmigot.brats_information.databinding.ActivityGalleryBinding
import androidx.viewpager.widget.ViewPager.OnPageChangeListener




class GalleryActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbarWithNavigationDrawer()
        setNavigationViewListener()
        prepareBottomNavigation()
        rawImagesT1 = (application as MyApp).imageRepository.rawImages_t1
        rawImagesT1c = (application as MyApp).imageRepository.rawImages_t1c
        rawImagesT2 = (application as MyApp).imageRepository.rawImages_t2
        rawImagesFlair = (application as MyApp).imageRepository.rawImages_flair
        segmentedImages = (application as MyApp).imageRepository.segmentedImages
        expertImages = (application as MyApp).imageRepository.expertImages
        viewPager = findViewById(R.id.rawSegImageViewPager)
        viewPagerAdapter = RawAndSegmentedImageViewPagerAdapter(
            this.applicationContext,
            rawImagesT1, rawImagesT1c, rawImagesT2, rawImagesFlair,
            segmentedImages
        )
        viewPager.adapter = viewPagerAdapter
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.i("ViewPager LOG ->> ", viewPager.currentItem.toString())
                viewPagerPosition = viewPager.currentItem
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.i("ViewPager LOG ->> ", viewPager.currentItem.toString())
                viewPagerPosition = viewPager.currentItem
            }

            override fun onPageSelected(position: Int) {
                Log.i("ViewPager LOG ->> ", viewPager.currentItem.toString())
                viewPagerPosition = viewPager.currentItem
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val drawerLayout = findViewById<DrawerLayout>(R.id.gallery_drawer_layout)
            drawerLayout.openDrawer(Gravity.LEFT)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBinding() {
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun setupToolbarWithNavigationDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.gallery_drawer_layout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.gallery_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun prepareBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.gallery_bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_item_raw_and_seg -> {
                    viewPagerAdapter = RawAndSegmentedImageViewPagerAdapter(
                        this.applicationContext,
                        rawImagesT1, rawImagesT1c, rawImagesT2, rawImagesFlair,
                        segmentedImages
                    )
                    viewPager.adapter = viewPagerAdapter
                    true
                }

                R.id.bottom_nav_item_model_and_expert -> {
                    viewPagerAdapter = ModelAndExpertImageViewPagerAdapter(
                        this.applicationContext,
                        expertImages = expertImages,
                        modelImages = segmentedImages
                    )
                    viewPager.adapter = viewPagerAdapter
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        lateinit var rawImagesT1: MutableMap<String, Bitmap>
        lateinit var rawImagesT1c: MutableMap<String, Bitmap>
        lateinit var rawImagesT2: MutableMap<String, Bitmap>
        lateinit var rawImagesFlair: MutableMap<String, Bitmap>
        lateinit var segmentedImages: MutableMap<String, Bitmap>
        lateinit var expertImages: MutableMap<String, Bitmap>
        var viewPagerPosition: Int = 0
    }
}