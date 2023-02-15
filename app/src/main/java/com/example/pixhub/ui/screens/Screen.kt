package com.example.pixhub.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector) {
    object Home : Screen("Home", Icons.Default.Home)
    object Bookmarks : Screen("Bookmarks", Icons.Default.Favorite)
    object Downloads : Screen("Downloads", Icons.Default.ArrowDropDown)
    object History : Screen("History", Icons.Default.Clear)
    object Profile : Screen("Profile", Icons.Default.Person)
    object Settings : Screen("Settings", Icons.Default.Settings)
    object About : Screen("About us", Icons.Default.Info)
    object Help : Screen("Help", Icons.Default.Refresh)
}

internal val screens = listOf(
    Screen.Home,
    Screen.Bookmarks,
    Screen.Downloads,
    Screen.History,
    Screen.Profile,
    Screen.Settings,
    Screen.About,
    Screen.Help
)