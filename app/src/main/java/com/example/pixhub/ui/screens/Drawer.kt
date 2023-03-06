package com.example.pixhub.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 18.sp)
    }
}

@Composable
fun Drawer(
    selectedDrawerItem: String,
    drawerLazyListState: LazyListState,
    itemTextStyle: TextStyle = TextStyle(fontSize = 16.sp),
    onItemClick: (String) -> Unit = {}
) {
    LazyColumn(state = drawerLazyListState) {
        item {
            DrawerHeader()
        }
        items(screens)
        { item ->
            Row(
                modifier = Modifier
                    .background(
                        if (selectedDrawerItem == item.title) {
                            Color.DarkGray.copy(0.1f)
                        } else {
                            Color.White
                        }
                    )
                    .padding(vertical = 1.dp)
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item.title)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}