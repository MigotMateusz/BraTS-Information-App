package pl.mateuszmigot.brats_information

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLanguage()
    }

    override fun onStart() {
        super.onStart()
        setLanguage()
    }

    override fun onResume() {
        super.onResume()
        setLanguage()
    }

    private fun setLanguage() {
        val sharedPreferences = this.getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE)
        val locale = getLocaleFromSharedPreference(sharedPreferences)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        if(isLanguageChanged) {
            finish()
            startActivity(intent)
            changeIsLanguageChangedFlag()
        }
    }

    fun setNavigationViewListener() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.left_side_menu_main_activity -> {
                    goToMainActivity()
                }
                R.id.left_side_menu_gallery_activity -> {
                    goToGalleryActivity()
                }
                R.id.left_side_menu_data_activity -> {
                    goToDataActivity()
                }
            }
            true
        })
    }

    fun goToSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGalleryActivity() {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    fun goToDataActivity() {
        val intent = Intent(this, DataActivity::class.java)
        startActivity(intent)
    }

    companion object {
        var isLanguageChanged = false

        fun changeIsLanguageChangedFlag() {
            isLanguageChanged = !isLanguageChanged
        }

        fun getLocaleFromSharedPreference(sharedPreferences: SharedPreferences) : Locale {
            val pine = sharedPreferences.getString("language", "en")
            return Locale(pine)
        }
    }
}