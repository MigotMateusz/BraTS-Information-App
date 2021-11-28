package pl.mateuszmigot.brats_information.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import pl.mateuszmigot.brats_information.R
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

    fun setNavigationViewListener() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
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
        }
    }

    fun goToSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun setLanguage() {
        val locale = getLocaleFromSharedPreferences()
        Locale.setDefault(locale)
        updateConfigWithNewLanguage(locale)
        if (isLanguageChanged) {
            refreshActivity()
        }
    }

    private fun getLocaleFromSharedPreferences(): Locale {
        val sharedPreferences = this.getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE)
        return getLocaleFromSharedPreference(sharedPreferences)
    }

    private fun refreshActivity() {
        finish()
        startActivity(intent)
        changeIsLanguageChangedFlag()
    }

    private fun updateConfigWithNewLanguage(locale: Locale) {
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goToGalleryActivity() {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    private fun goToDataActivity() {
        val intent = Intent(this, DataActivity::class.java)
        startActivity(intent)
    }

    companion object {
        var isLanguageChanged = false

        fun changeIsLanguageChangedFlag() {
            isLanguageChanged = !isLanguageChanged
        }

        fun getLocaleFromSharedPreference(sharedPreferences: SharedPreferences): Locale {
            val savedLanguage = sharedPreferences.getString("language", "en")
            return Locale(savedLanguage)
        }
    }
}