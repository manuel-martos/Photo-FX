package com.mmartosdev.photofx.ui.photofx

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartosdev.photofx.ui.EffectConfig
import com.mmartosdev.photofx.ui.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class PhotoFxViewModel : ViewModel() {
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