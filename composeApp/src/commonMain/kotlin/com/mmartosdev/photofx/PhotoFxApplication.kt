package com.mmartosdev.photofx

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mmartosdev.photofx.theme.AppTheme
import com.mmartosdev.photofx.ui.EffectConfig
import com.mmartosdev.photofx.ui.photofx.PhotoFxScreen
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.imageResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.img_01
import photo_fx.composeapp.generated.resources.img_02
import photo_fx.composeapp.generated.resources.img_03
import photo_fx.composeapp.generated.resources.img_04
import photo_fx.composeapp.generated.resources.img_05
import photo_fx.composeapp.generated.resources.img_06

@Composable
internal fun PhotoFxApplication(
    onCloseClicked: (() -> Unit)?,
) = AppTheme {
    val images = persistentListOf(
        imageResource(Res.drawable.img_01),
        imageResource(Res.drawable.img_02),
        imageResource(Res.drawable.img_03),
        imageResource(Res.drawable.img_04),
        imageResource(Res.drawable.img_05),
        imageResource(Res.drawable.img_06),
    )
    val effects = persistentListOf(
        EffectConfig.Vignette(),
        EffectConfig.SmoothPixelation(),
        EffectConfig.ChromaticAberration(),
    )
    PhotoFxScreen(
        images = images,
        effects = effects,
        onCloseClicked = onCloseClicked,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    )
}
