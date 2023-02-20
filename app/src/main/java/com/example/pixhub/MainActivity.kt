package com.example.pixhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.pixhub.ui.screens.*
import com.example.pixhub.ui.theme.PixHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixHubTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                AppScreen(drawerState,navController, scope)
            }
        }
    }
}