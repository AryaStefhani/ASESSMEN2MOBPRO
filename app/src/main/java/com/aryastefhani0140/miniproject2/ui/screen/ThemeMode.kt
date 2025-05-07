package com.aryastefhani0140.miniproject2.ui.screen

enum class ThemeMode(val value: Int) {
    SYSTEM(0),
    LIGHT(1),
    DARK(2);

    companion object {
        fun fromValue(value: Int): ThemeMode {
            return when (value) {
                1 -> LIGHT
                2 -> DARK
                else -> SYSTEM
            }
        }
    }
}