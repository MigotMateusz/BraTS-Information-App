package pl.mateuszmigot.brats_information.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.activities.BaseActivity
import pl.mateuszmigot.brats_information.activities.SettingsActivity
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var darkModeSwitch: SwitchPreference
    private lateinit var languageListPreference: ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        setupPreferenceElements()
        setListPreferenceCheckedItem(languageListPreference)
        handleDarkModeSwitch()
        handleLanguageListPreference()
    }

    private fun handleDarkModeSwitch() {
        darkModeSwitch.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, _ ->
                changeThemeMode(darkModeSwitch)
                false
            }
    }

    private fun changeThemeMode(darkModeSwitch: SwitchPreference) {
        if (darkModeSwitch.isChecked) {
            changeToLightMode()
        } else {
            changeToDarkMode()
        }
    }

    private fun changeToLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        saveDarkModeStateInSharedPreferences(false)
        Toast.makeText(activity, "Dark Mode Off", Toast.LENGTH_SHORT).show()
        darkModeSwitch.isChecked = false
    }

    private fun changeToDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        saveDarkModeStateInSharedPreferences(true)
        Toast.makeText(activity, "Light Mode On", Toast.LENGTH_SHORT).show()
        darkModeSwitch.isChecked = true
    }

    private fun setupPreferenceElements() {
        darkModeSwitch = findPreference<SwitchPreference>("darkMode") as SwitchPreference
        languageListPreference = findPreference<ListPreference>("list") as ListPreference
        getDarkModeSwitchStateFromSharedPreferences()
    }

    private fun getDarkModeSwitchStateFromSharedPreferences() {
        val sharedPreferences = context?.getSharedPreferences("darkMode", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            val isNightModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)
            darkModeSwitch.isChecked = isNightModeOn
        } else {
            darkModeSwitch.isChecked = false;
        }
    }

    private fun handleLanguageListPreference() {
        languageListPreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                changeApplicationLanguage(newValue.toString())
                false
            }
    }

    private fun changeApplicationLanguage(lang: String) {
        val myLocale = Locale(lang)
        updateConfigWithNewLocale(myLocale)
        saveLocaleInSharedPreferences(myLocale)
        refreshActivity()
        changeIsLanguageChangedFlagToTrue()
    }

    private fun updateConfigWithNewLocale(myLocale: Locale) {
        val displayMetrics = activity?.resources?.displayMetrics
        val config = activity?.resources?.configuration
        config?.setLocale(myLocale)
        activity?.resources?.updateConfiguration(config, displayMetrics)
    }

    private fun changeIsLanguageChangedFlagToTrue() {
        BaseActivity.isLanguageChanged = true
    }

    private fun refreshActivity() {
        val intent = Intent(context, SettingsActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

    private fun saveLocaleInSharedPreferences(locale: Locale) {
        val sharedPreferences =
            context?.getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            writeNewValueToLanguageStringInSharedPreferences(sharedPreferences, locale.language)
            Toast.makeText(context, "Language changed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDarkModeStateInSharedPreferences(isDarkModeOn: Boolean) {
        val sharedPreferences =
            context?.getSharedPreferences("darkMode", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            writeNewValueToDarkModeStateInSharedPreferences(sharedPreferences, isDarkModeOn)
        }
    }

    private fun writeNewValueToLanguageStringInSharedPreferences(
        sharedPreferences: SharedPreferences,
        language: String
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("language", language)
        editor.apply()
    }

    private fun writeNewValueToDarkModeStateInSharedPreferences(
        sharedPreferences: SharedPreferences,
        state: Boolean
    ) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkModeOn", state)
        editor.apply()
    }

    private fun setListPreferenceCheckedItem(listPreference: ListPreference) {
        val sharedPreferences =
            context?.getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            val locale = BaseActivity.getLocaleFromSharedPreference(sharedPreferences)
            if (locale.language.equals("en")) {
                listPreference.setValueIndex(0)
            } else {
                listPreference.setValueIndex(1)
            }
        }
    }
}
