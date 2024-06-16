package com.mmartosdev.photofx.ui.effectselection

import androidx.lifecycle.ViewModel
import com.mmartosdev.photofx.ui.EffectConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EffectSelectionViewModel : ViewModel() {
    private val _selectedEffect: MutableStateFlow<EffectConfig?> = MutableStateFlow(null)
    val selectedEffect = _selectedEffect.asStateFlow()

    fun selectEffect(effect: EffectConfig) {
        _selectedEffect.update { effect }
    }
}