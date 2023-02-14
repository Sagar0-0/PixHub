package com.example.pixhub.ui.screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.pixhub.utils.customDrawerShape
import com.example.pixhub.utils.toPx
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
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = drawerScreens,
                onItemClick = {
                    Log.d(TAG, "MainScreen: Clicked")
                    scope.launch {  scaffoldState.drawerState.close() }
                }
            )

        },
        drawerShape = customDrawerShape(LocalConfiguration.current.screenHeightDp.dp.toPx()),
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) {
        ScreenHeader()
        MainScreenContent()
    }
}

@Composable
fun MainScreenContent() {
    Text(text = "MainContent")
}