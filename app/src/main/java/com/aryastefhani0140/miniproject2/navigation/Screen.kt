package com.aryastefhani0140.miniproject2.navigation

import com.aryastefhani0140.miniproject2.ui.screen.KEY_ID_TABUNGAN

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object FormBaru : Screen("detailScreen")
    data object FormUbah : Screen("detailScreen/{$KEY_ID_TABUNGAN}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
    data object RecycleBin : Screen("recycleBinScreen")
    data object ThemeSettings : Screen("themeScreen")
}