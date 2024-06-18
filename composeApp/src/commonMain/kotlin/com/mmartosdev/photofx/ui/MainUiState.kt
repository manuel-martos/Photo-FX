package com.mmartosdev.photofx.ui

import androidx.compose.ui.graphics.ImageBitmap

sealed interface MainUiState {
    data object PhotoSelection : MainUiState

    data class EffectSelection(val imageBitmap: ImageBitmap) : MainUiState

    data class Playground(
        val effect: EffectConfig,
        val imageBitmap: ImageBitmap,
    ) : MainUiState
}
