package pl.mateuszmigot.brats_information

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import java.util.*


class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var darkModeSwitch : SwitchPreference
    private lateinit var languageListPreference : ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        darkModeSwitch = findPreference<SwitchPreference>("darkMode") as SwitchPreference
        languageListPreference = findPreference<ListPreference>("list") as ListPreference
        setListPreferenceCheckedItem(languageListPreference)
        handleDarkModeSwitch()
        handleLanguageListPreference()
    }

    private fun handleDarkModeSwitch() {
        darkModeSwitch.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, _ ->
                if (darkModeSwitch.isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    Toast.makeText(activity, "Dark Mode Off", Toast.LENGTH_SHORT).show()
                    darkModeSwitch.isChecked = false
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    Toast.makeText(activity, "Light Mode On", Toast.LENGTH_SHORT).show()
                    darkModeSwitch.isChecked = true
                }
                false
            }
    }

    private fun handleLanguageListPreference() {

        languageListPreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                Log.i("SettingFragment", preference.toString())
                Log.i("SettingFragment", newValue.toString())
                setLocale(newValue.toString())
                false
            }
    }

    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val displayMetrics = activity?.resources?.displayMetrics
        val config = activity?.resources?.configuration
        config?.setLocale(myLocale)
        activity?.resources?.updateConfiguration(config, displayMetrics)
        saveLocaleInSharedPreferences(myLocale)
        val intent = Intent(context, SettingsActivity::class.java)
        activity?.finish()
        startActivity(intent)
        BaseActivity.isLanguageChanged = true
    }

    private fun saveLocaleInSharedPreferences(locale: Locale) {
        val sharedPreferences =
            context?.getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            writeNewValueToSharedPreferences(sharedPreferences, locale.language)
            Toast.makeText(context, "Language changed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun writeNewValueToSharedPreferences(
        sharedPreferences: SharedPreferences,
        language: String
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("language", language)
        editor.apply()
    }

    private fun setListPreferenceCheckedItem(listPreference: ListPreference) {
        val sharedPreferences = context?.getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE)
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
