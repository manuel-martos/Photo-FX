package com.mmartosdev.photofx.ui.playground

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mmartosdev.photofx.compose.GenericLayout
import com.mmartosdev.photofx.ui.EffectConfig
import com.mmartosdev.photofx.ui.effectselection.applyEffect
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.effect_controls_chromatic_aberration_intensity
import photo_fx.composeapp.generated.resources.effect_controls_smooth_pixelation_pixel_size
import photo_fx.composeapp.generated.resources.effect_controls_vignette_decay_factor
import photo_fx.composeapp.generated.resources.effect_controls_vignette_intensity
import photo_fx.composeapp.generated.resources.effect_selection_image_with_effect
import photo_fx.composeapp.generated.resources.generic_accept
import photo_fx.composeapp.generated.resources.generic_reset
import photo_fx.composeapp.generated.resources.playground_title

@Composable
fun PlaygroundScreen(
    selectedImage: ImageBitmap,
    selectedEffect: EffectConfig,
    onCloseClicked: (() -> Unit)?,
    onRefreshClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlaygroundScreenViewModel = viewModel { PlaygroundScreenViewModel(selectedEffect) },
) {
    val effectConfig by viewModel.effectConfig.collectAsState()
    GenericLayout(
        title = stringResource(resource = Res.string.playground_title),
        onCloseClicked = onCloseClicked,
        actions = {
            IconButton(onClick = onRefreshClicked) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(Res.string.generic_reset)
                )
            }
            IconButton(onClick = { /* no-op */ }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(Res.string.generic_accept)
                )
            }
        },
        mainContent = {
            Image(
                bitmap = selectedImage,
                contentDescription = stringResource(Res.string.effect_selection_image_with_effect),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .applyEffect(effectConfig)
            )
        },
        additionalContent = {
            EffectControls(
                effectConfig = effectConfig,
                onEffectConfigChanged = viewModel::onEffectConfigChanged,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun EffectControls(
    effectConfig: EffectConfig,
    onEffectConfigChanged: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (effectConfig) {
        is EffectConfig.Vignette ->
            VignetteEffectControls(
                effectConfig = effectConfig,
                onEffectConfigChanged = onEffectConfigChanged,
                modifier = modifier,
            )

        is EffectConfig.SmoothPixelation ->
            SmoothPixelationEffectControls(
                effectConfig = effectConfig,
                onEffectConfigChanged = onEffectConfigChanged,
                modifier = modifier,
            )

        is EffectConfig.ChromaticAberration ->
            ChromaticAberrationEffectControls(
                effectConfig = effectConfig,
                onEffectConfigChanged = onEffectConfigChanged,
                modifier = modifier,
            )
    }
}

@Composable
private fun VignetteEffectControls(
    effectConfig: EffectConfig.Vignette,
    onEffectConfigChanged: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        SlidingFactor(
            name = stringResource(Res.string.effect_controls_vignette_intensity),
            value = effectConfig.intensity,
            onValueChanged = { onEffectConfigChanged(effectConfig.copy(intensity = it)) },
            valueRange = 1f..256f,
        )
        SlidingFactor(
            name = stringResource(Res.string.effect_controls_vignette_decay_factor),
            value = effectConfig.decayFactor,
            onValueChanged = { onEffectConfigChanged(effectConfig.copy(decayFactor = it)) },
            valueRange = 0.0001f..4f,
        )
    }
}

@Composable
private fun SmoothPixelationEffectControls(
    effectConfig: EffectConfig.SmoothPixelation,
    onEffectConfigChanged: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        SlidingFactor(
            name = stringResource(Res.string.effect_controls_smooth_pixelation_pixel_size),
            value = effectConfig.pixelSize,
            onValueChanged = { onEffectConfigChanged(effectConfig.copy(pixelSize = it)) },
            valueRange = 1f..4f,
        )
    }
}

@Composable
private fun ChromaticAberrationEffectControls(
    effectConfig: EffectConfig.ChromaticAberration,
    onEffectConfigChanged: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        SlidingFactor(
            name = stringResource(Res.string.effect_controls_chromatic_aberration_intensity),
            value = effectConfig.intensity,
            onValueChanged = { onEffectConfigChanged(effectConfig.copy(intensity = it)) },
            valueRange = 1f..16f,
        )
    }
}

@Composable
private fun SlidingFactor(
    name: String,
    value: Float,
    onValueChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(name)
        Slider(
            value = value,
            onValueChange = onValueChanged,
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}