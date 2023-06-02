package com.bagusok.github.ui.main.setting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bagusok.github.databinding.ActivitySettingBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    binding.tgDark.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    binding.tgDark.isChecked = false
                }
            })

        binding.tgDark.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }

        binding.backBtnProfile.setOnClickListener {
            onBackPressed()
        }
    }
}


