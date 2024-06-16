package com.mmartosdev.photofx.ui.effectselection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mmartosdev.photofx.compose.SelectionIndicator
import com.mmartosdev.photofx.compose.modifiers.chromaticAberrationShader
import com.mmartosdev.photofx.compose.modifiers.smoothPixelationShader
import com.mmartosdev.photofx.compose.modifiers.vignetteShader
import com.mmartosdev.photofx.ui.EffectConfig
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.effect_gallery_chromatic_aberration
import photo_fx.composeapp.generated.resources.effect_gallery_smooth_pixelation
import photo_fx.composeapp.generated.resources.effect_gallery_vignette

@Composable
fun EffectGallery(
    columns: Int,
    imageBitmap: ImageBitmap,
    effects: ImmutableList<EffectConfig>,
    selectedEffect: EffectConfig,
    onEffectSelected: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        horizontalArrangement = spacedBy(2.dp),
        verticalArrangement = spacedBy(2.dp),
        modifier = modifier,
    ) {
        items(items = effects) { effect ->
            EffectItem(
                effect = effect,
                imageBitmap = imageBitmap,
                isSelected = selectedEffect::class.isInstance(effect),
                onEffectSelected = onEffectSelected,
                modifier = Modifier.aspectRatio(16f / 9f)
            )
        }
    }
}

@Composable
private fun EffectItem(
    effect: EffectConfig,
    imageBitmap: ImageBitmap,
    isSelected: Boolean,
    onEffectSelected: (EffectConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clickable { onEffectSelected(effect) }
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = effect.contentDescription(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .applyEffect(effect),
        )
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
                .size(18.dp)
        ) {
            SelectionIndicator()
        }
    }
}

fun Modifier.applyEffect(effectConfig: EffectConfig): Modifier =
    this then when (effectConfig) {
        is EffectConfig.Vignette ->
            Modifier.vignetteShader(effectConfig.intensity, effectConfig.decayFactor)

        is EffectConfig.SmoothPixelation ->
            Modifier.smoothPixelationShader(effectConfig.pixelSize)

        is EffectConfig.ChromaticAberration ->
            Modifier.chromaticAberrationShader(effectConfig.intensity)
    }

@Composable
private fun EffectConfig.contentDescription() =
    when (this) {
        is EffectConfig.Vignette ->
            stringResource(Res.string.effect_gallery_vignette)

        is EffectConfig.SmoothPixelation ->
            stringResource(Res.string.effect_gallery_smooth_pixelation)

        is EffectConfig.ChromaticAberration ->
            stringResource(Res.string.effect_gallery_chromatic_aberration)
    }
