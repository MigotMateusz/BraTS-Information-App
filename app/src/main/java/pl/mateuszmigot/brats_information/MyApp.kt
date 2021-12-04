package pl.mateuszmigot.brats_information

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.FirebaseApp
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.hilt.android.HiltAndroidApp
import pl.mateuszmigot.brats_information.repositories.CloudStorageRepository
import pl.mateuszmigot.brats_information.repositories.TeamsViewModel
import javax.inject.Inject


class MyApp : Application() {


    lateinit var imageRepository: CloudStorageRepository
    lateinit var teamsViewModel: TeamsViewModel
    override fun onCreate() {
        super.onCreate()
        setupTeamsViewModel()
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
    private fun setupTeamsViewModel() {
        teamsViewModel = TeamsViewModel()
        teamsViewModel.loadAllTeams()
    }

    private fun setupImageRepository() {
        imageRepository = CloudStorageRepository()
        imageRepository.populateRawImages()
        imageRepository.populateSegmentedModelImages()
        imageRepository.populateExpertImages()
    }
}