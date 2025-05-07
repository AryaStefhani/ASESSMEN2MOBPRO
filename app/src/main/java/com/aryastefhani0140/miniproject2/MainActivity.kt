package com.aryastefhani0140.miniproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.aryastefhani0140.miniproject2.navigation.SetupNavGraph
import com.aryastefhani0140.miniproject2.ui.screen.ThemeMode
import com.aryastefhani0140.miniproject2.ui.theme.Miniproject2Theme

import com.aryastefhani0140.miniproject2.util.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = SettingsDataStore(this)
        val themeFlow = dataStore.themeFlow.stateIn(
            scope = lifecycleScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

        setContent {
            val themeMode by themeFlow.collectAsState()
            val darkTheme = when (ThemeMode.fromValue(themeMode)) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            Miniproject2Theme(darkTheme = darkTheme) {
                SetupNavGraph()
            }
        }
    }
}
