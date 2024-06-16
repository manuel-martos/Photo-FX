package com.mmartosdev.photofx.ui.photofx

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mmartosdev.photofx.ui.EffectConfig
import com.mmartosdev.photofx.ui.effectselection.EffectSelectionScreen
import com.mmartosdev.photofx.ui.MainUiState
import com.mmartosdev.photofx.ui.photoselection.PhotoSelectionScreen
import com.mmartosdev.photofx.ui.playground.PlaygroundScreen
import kotlinx.collections.immutable.ImmutableList

@Composable
fun PhotoFxScreen(
    images: ImmutableList<ImageBitmap>,
    effects: ImmutableList<EffectConfig>,
    onCloseClicked: (() -> Unit)?,
    modifier: Modifier = Modifier,
    viewModel: PhotoFxScreenViewModel = viewModel { PhotoFxScreenViewModel() },
) {
    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = modifier) {
        when (val state = uiState) {
            is MainUiState.PhotoSelection ->
                ScopedViewModelStore {
                    PhotoSelectionScreen(
                        images = images,
                        onCloseClicked = onCloseClicked,
                        onImageSelected = viewModel::onImageSelected,
                    )
                }

            is MainUiState.EffectSelection ->
                ScopedViewModelStore {
                    EffectSelectionScreen(
                        effects = effects,
                        selectedImage = state.imageBitmap,
                        onCloseClicked = onCloseClicked,
                        onEffectSelected = viewModel::onEffectSelected,
                    )
                }

            is MainUiState.Playground ->
                ScopedViewModelStore {
                    PlaygroundScreen(
                        selectedImage = state.imageBitmap,
                        selectedEffect = state.effect,
                        onCloseClicked = onCloseClicked,
                        onRefreshClicked = viewModel::resetSelections,
                    )
                }
        }
    }
}

@Composable
private fun ScopedViewModelStore(
    content: @Composable () -> Unit,
) {
    val scopedViewModelStoreOwner = object : ViewModelStoreOwner {
        override val viewModelStore: ViewModelStore = ViewModelStore()
    }

    CompositionLocalProvider(LocalViewModelStoreOwner provides scopedViewModelStoreOwner) {
        content.invoke()
    }
}
