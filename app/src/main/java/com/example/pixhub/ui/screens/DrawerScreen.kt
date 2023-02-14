package com.example.pixhub.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerScreen(val title: String, val icon: ImageVector) {
    object Home : DrawerScreen("Home", Icons.Default.Home)
    object Bookmarks : DrawerScreen("Bookmarks", Icons.Default.Favorite)
    object Downloads : DrawerScreen("Downloads", Icons.Default.ArrowDropDown)
    object History : DrawerScreen("History", Icons.Default.Clear)
    object Profile : DrawerScreen("Profile", Icons.Default.Person)
    object Settings : DrawerScreen("Settings", Icons.Default.Settings)
    object About : DrawerScreen("About us", Icons.Default.Info)
    object Help : DrawerScreen("Help", Icons.Default.Refresh)
}

internal val drawerScreens = listOf(
    DrawerScreen.Home,
    DrawerScreen.Bookmarks,
    DrawerScreen.Downloads,
    DrawerScreen.History,
    DrawerScreen.Profile,
    DrawerScreen.Settings,
    DrawerScreen.About,
    DrawerScreen.Help
)