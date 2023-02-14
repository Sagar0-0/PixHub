package com.example.pixhub.ui.screens


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
    fieldHint: String
) {
    TopAppBar {
        IconButton(onClick = onNavigationIconClick) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle Drawer")
        }
        val inputText = rememberSaveable {
            mutableStateOf("")
        }
        TextField(
            value = inputText.value,
            onValueChange = {
                inputText.value = it
            },
            placeholder = { Text(text = fieldHint) },
        )
    }
}
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