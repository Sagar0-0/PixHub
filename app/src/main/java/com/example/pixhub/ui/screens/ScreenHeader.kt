package com.example.pixhub.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeader(
    onNavigationIconClick: () -> Unit,
    fieldHint: String
) {
    Row(
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 5.dp,
                end = 10.dp,
                bottom = 10.dp,
            )
            .fillMaxWidth()
    ) {
        val inputText = rememberSaveable {
            mutableStateOf("")
        }
        val focusManager = LocalFocusManager.current
        TextField(
            value = inputText.value,
            onValueChange = {
                inputText.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Drawer Icon",
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false, radius = 16.dp)
                    ) {
                        onNavigationIconClick()
                    }
                )
            },
            trailingIcon = {
                val visible = remember {
                    derivedStateOf {
                        inputText.value.isNotBlank()
                    }
                }
                if (visible.value) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Button",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false, radius = 16.dp)
                        ) {
                            onNavigationIconClick()
                        }
                    )
                }
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = true,
            placeholder = { Text(text = fieldHint) },
            modifier = Modifier.weight(1f)
        )
    }
}
