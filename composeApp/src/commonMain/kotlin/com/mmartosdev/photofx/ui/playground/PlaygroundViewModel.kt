package com.mmartosdev.photofx.ui.playground

import androidx.lifecycle.ViewModel
import com.mmartosdev.photofx.ui.EffectConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlaygroundViewModel(
    effect: EffectConfig,
) : ViewModel() {
    private val _effectConfig: MutableStateFlow<EffectConfig> = MutableStateFlow(effect)
    val effectConfig: StateFlow<EffectConfig> = _effectConfig.asStateFlow()

    fun onEffectConfigChanged(config: EffectConfig) {
        _effectConfig.update { config }
    }

}
