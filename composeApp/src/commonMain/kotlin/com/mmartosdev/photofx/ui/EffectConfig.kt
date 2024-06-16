package com.mmartosdev.photofx.ui

sealed interface EffectConfig {
    data class Vignette(val intensity: Float = 16f, val decayFactor: Float = 0.25f) : EffectConfig
    data class SmoothPixelation(val pixelSize: Float = 2f) : EffectConfig
    data class ChromaticAberration(val intensity: Float = 8f) : EffectConfig
}