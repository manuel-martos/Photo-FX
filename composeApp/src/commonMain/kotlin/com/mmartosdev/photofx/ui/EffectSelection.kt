package com.mmartosdev.photofx.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.effect_selection_image_with_effect
import photo_fx.composeapp.generated.resources.effect_selection_title
import photo_fx.composeapp.generated.resources.generic_next

@Composable
fun EffectSelection(
    effects: ImmutableList<EffectConfig>,
    selectedImage: ImageBitmap,
    onCloseClicked: (() -> Unit)?,
    onEffectSelected: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EffectSelectionViewModel = viewModel { EffectSelectionViewModel() },
) {
    val selectedEffect = viewModel.selectedEffect.collectAsState().value ?: effects.first()
    GenericLayout(
        title = stringResource(resource = Res.string.effect_selection_title),
        onCloseClicked = onCloseClicked,
        actions = {
            IconButton(onClick = { onEffectSelected(selectedEffect) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(resource = Res.string.generic_next)
                )
            }
        },
        mainContent = {
            SelectedImageWithEffect(
                selectedImage = selectedImage,
                selectedEffect = selectedEffect,
            )
        },
        additionalContent = {
            EffectGallery(
                columns = 3,
                imageBitmap = selectedImage,
                effects = effects,
                selectedEffect = selectedEffect,
                onEffectSelected = { viewModel.selectEffect(it) },
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun SelectedImageWithEffect(
    selectedImage: ImageBitmap,
    selectedEffect: EffectConfig,
    modifier: Modifier = Modifier,
) {
    Crossfade(
        targetState = selectedEffect,
        modifier = modifier,
    ) {
        Image(
            bitmap = selectedImage,
            contentDescription = stringResource(resource = Res.string.effect_selection_image_with_effect),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .applyEffect(it)
        )
    }
}
