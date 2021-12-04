package pl.mateuszmigot.brats_information

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.FirebaseApp
import pl.mateuszmigot.brats_information.repositories.CloudStorageRepository
import pl.mateuszmigot.brats_information.repositories.FirestoreRepository
import pl.mateuszmigot.brats_information.repositories.TeamsViewModel


class MyApp : Application() {


    lateinit var imageRepository: CloudStorageRepository
    lateinit var teamsViewModel: TeamsViewModel
    lateinit var firestoreRepository: FirestoreRepository

    override fun onCreate() {
        super.onCreate()
        setupTeamsViewModel()
        FirebaseApp.initializeApp(this)
        setupImageRepository()
        firestoreRepository = FirestoreRepository()
        firestoreRepository.getMyModels()
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
        teamsViewModel.loadTop10Teams()
        teamsViewModel.loadMyModelRanking()
    }

    private fun setupImageRepository() {
        imageRepository = CloudStorageRepository()
        imageRepository.populateRawImages()
        imageRepository.populateSegmentedModelImages()
        imageRepository.populateExpertImages()
    }
}