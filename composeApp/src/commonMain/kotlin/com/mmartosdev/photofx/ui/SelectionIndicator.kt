package com.mmartosdev.photofx.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.selection_indicator

@Composable
fun SelectionIndicator() {
    val shape = CircleShape
    val borderColor = MaterialTheme.colorScheme.background
    val backgroundColor = borderColor.copy(alpha = 0.6f)
    Icon(
        imageVector = Icons.Default.Check,
        contentDescription = stringResource(Res.string.selection_indicator),
        modifier = Modifier
            .fillMaxSize()
            .border(
                color = borderColor,
                width = 1.dp,
                shape = shape
            )
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(2.dp)
    )
}