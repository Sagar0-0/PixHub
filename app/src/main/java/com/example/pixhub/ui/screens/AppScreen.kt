package com.example.pixhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerLazyListState: LazyListState = rememberLazyListState()
) {
    val imageViewModel: ImageViewModel = hiltViewModel()
    var selectedDrawerItem by rememberSaveable {
        mutableStateOf(Screen.Home.title)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            Drawer(drawerLazyListState = drawerLazyListState, selectedDrawerItem = selectedDrawerItem) { route ->
                selectedDrawerItem = route
                scope.launch { scaffoldState.drawerState.close() }
                navController.navigate(route) {
                    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return@navigate) {
                        inclusive =  true
                    }
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
            navController.navigate(Screen.Images.title) {
                launchSingleTop = true
            }
            focusManager.clearFocus()
        }
        NavigationHost(navController, imageViewModel)
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
            ImageSearchGrid(navController, imageViewModel)
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
