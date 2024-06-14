package com.mmartosdev.photofx.ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PhotoSelectionViewModel : ViewModel() {
    private val _selectedImage: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)
    val selectedImage = _selectedImage.asStateFlow()

    fun onImageSelected(imageBitmap: ImageBitmap) {
        _selectedImage.update { imageBitmap }
    }
}