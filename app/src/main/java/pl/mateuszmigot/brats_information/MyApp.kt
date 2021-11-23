package pl.mateuszmigot.brats_information

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = this.getSharedPreferences("darkMode", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("isDarkModeOn", false)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}