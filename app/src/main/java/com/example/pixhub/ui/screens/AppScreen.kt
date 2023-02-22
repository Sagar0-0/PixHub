package com.example.pixhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pixhub.ui.data.ImageViewModel
import com.example.pixhub.utils.customDrawerShape
import com.example.pixhub.utils.toPx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
Discover
Bookmarks
Downloads
Search History
Profile
Settings
About us
Help

- CopyLink Share Bookmark Download
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppScreen(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    scope: CoroutineScope,
    imageViewModel: ImageViewModel = hiltViewModel()
    )
{
    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            Drawer { route ->
                scope.launch { scaffoldState.drawerState.close() }
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        },
        drawerElevation = 90.dp,
        drawerShape = customDrawerShape(LocalConfiguration.current.screenHeightDp.dp.toPx())
    ) {
        val focusManager = LocalFocusManager.current
        ScreenHeader(
            onMenuIconClick = {
                focusManager.clearFocus()
                scope.launch { scaffoldState.drawerState.open() }
            },
            fieldHint = "Search.."
        ) {
            imageViewModel.searchUnsplashImage(it)
            navController.navigate(Screen.Images.title){
                launchSingleTop = true
            }
            focusManager.clearFocus()
        }
        NavigationHost(navController,imageViewModel)
    }
}

@Composable
fun NavigationHost(navController: NavHostController,imageViewModel: ImageViewModel) {
    NavHost(
        modifier = Modifier.padding(top = 70.dp),
        navController = navController,
        startDestination = Screen.Home.title
    ) {
        composable(Screen.Home.title) {
            Home()
        }
        composable(Screen.Images.title) {
            ImageSearchGrid(navController,imageViewModel)
        }
        composable(Screen.Bookmarks.title) {
            Bookmarks()
        }
        composable(Screen.Downloads.title) {
            Downloads()
        }
        composable(Screen.History.title) {
            History()
        }
        composable(Screen.Profile.title) {
            Profile()
        }
        composable(Screen.Settings.title) {
            Settings()
        }
        composable(Screen.About.title) {
            About()
        }
        composable(Screen.Help.title) {
            Help()
        }
    }
}
