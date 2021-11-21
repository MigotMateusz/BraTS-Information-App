package pl.mateuszmigot.brats_information

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
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