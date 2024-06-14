package com.mmartosdev.photofx.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.generic_close

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericLayout(
    title: String,
    onCloseClicked: (() -> Unit)?,
    actions: @Composable RowScope.() -> Unit,
    mainContent: @Composable () -> Unit,
    additionalContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    onCloseClicked?.run {
                        IconButton(onClick = this) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(resource = Res.string.generic_close)
                            )
                        }
                    }
                },
                actions = actions
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        InnerLayout(
            mainContent = mainContent,
            additionalContent = additionalContent,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun InnerLayout(
    mainContent: @Composable () -> Unit,
    additionalContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier) {
        val isLandscape = shouldApplyLandscapeLayout()
        if (isLandscape) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3f),
                    content = { mainContent.invoke() }
                )
                Spacer(modifier = Modifier.size(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    content = { additionalContent.invoke() },
                )
            }
        } else {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f),
                    content = { mainContent.invoke() }
                )
                Spacer(modifier = Modifier.size(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    content = { additionalContent.invoke() }
                )
            }
        }
    }
}

private fun BoxWithConstraintsScope.shouldApplyLandscapeLayout() =
    0.6f * maxWidth.value > maxHeight.value
