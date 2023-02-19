package com.example.pixhub.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

fun customDrawerShape(height: Float) = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rounded(
            RoundRect(
                left = 0f,
                top = 0f,
                right = 900f,
                bottom = height,
                topRightCornerRadius = CornerRadius(x = 90f, y = 90f)
            )
        )
    }
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return this.value * density
}