package com.example.pixhub.ui.screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
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
                    scope.launch { scaffoldState.drawerState.close() }
                }
            )

        },
        drawerShape = customDrawerShape(LocalConfiguration.current.screenHeightDp.dp.toPx()),
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) {
        Row {
            val focusManager = LocalFocusManager.current
            ScreenHeader(
                onNavigationIconClick = {
                    focusManager.clearFocus()
                    scope.launch { scaffoldState.drawerState.open()  }
                }, fieldHint = "Search.."
            )
            Spacer(modifier = Modifier.padding(10.dp))
            MainScreenContent()
        }
    }
}

@Composable
fun MainScreenContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "MainContent", textAlign = TextAlign.Center)
    }
}