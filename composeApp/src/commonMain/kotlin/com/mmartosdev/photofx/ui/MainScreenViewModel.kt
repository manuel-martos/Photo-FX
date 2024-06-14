package com.mmartosdev.photofx.ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
    private val _selectedPhoto = MutableStateFlow<ImageBitmap?>(null)
    private val _selectedEffect = MutableStateFlow<EffectConfig?>(null)

    val uiState: StateFlow<MainUiState> =
        combine(_selectedPhoto, _selectedEffect) { imageBitmap, effect ->
            when {
                imageBitmap == null -> MainUiState.PhotoSelection
                effect == null -> MainUiState.EffectSelection(imageBitmap)
                else -> MainUiState.Playground(effect, imageBitmap)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainUiState.PhotoSelection
        )

    fun onImageSelected(imageBitmap: ImageBitmap) {
        _selectedPhoto.update { imageBitmap }
    }

    fun onEffectSelected(effect: EffectConfig) {
        _selectedEffect.update { effect }
    }

    fun resetSelections() {
        _selectedPhoto.update { null }
        _selectedEffect.update { null }
    }

}

sealed interface MainUiState {
    data object PhotoSelection : MainUiState

    data class EffectSelection(val imageBitmap: ImageBitmap) : MainUiState

    data class Playground(
        val effect: EffectConfig,
        val imageBitmap: ImageBitmap,
    ) : MainUiState
}

sealed interface EffectConfig {
    data class Vignette(val intensity: Float = 16f, val decayFactor: Float = 0.25f) : EffectConfig
    data class SmoothPixelation(val pixelSize: Float = 2f) : EffectConfig
    data class ChromaticAberration(val intensity: Float = 8f) : EffectConfig
}
