package pl.mateuszmigot.brats_information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        prepareToolbar()
        prepareFragmentLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun prepareToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun prepareFragmentLayout() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_fragment_frame_layout, SettingsFragment())
            .commit()
    }
}