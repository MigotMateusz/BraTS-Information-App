package pl.mateuszmigot.brats_information

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import pl.mateuszmigot.brats_information.repositories.CloudStorageRepository

@HiltAndroidApp
class MyApp : Application() {

    lateinit var imageRepository: CloudStorageRepository
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        setupImageRepository()
        val sharedPreferences = this.getSharedPreferences("darkMode", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("isDarkModeOn", false)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private fun setupImageRepository() {
        imageRepository = CloudStorageRepository()
        imageRepository.populateRawImages()
        imageRepository.populateSegmentedModelImages()
        imageRepository.populateExpertImages()
    }
}