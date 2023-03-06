package com.example.pixhub.utils

import androidx.compose.runtime.Composable

data class ImageTabItem(
    val apiType: ApiType,
    val screen: @Composable ()->Unit
)
