package com.ghedeon.vitrine.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
}