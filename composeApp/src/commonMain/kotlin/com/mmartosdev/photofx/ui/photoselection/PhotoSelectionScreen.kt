package com.mmartosdev.photofx.ui.photoselection

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mmartosdev.photofx.compose.GenericLayout
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.photo_selection_title

@Composable
fun PhotoSelectionScreen(
    images: ImmutableList<ImageBitmap>,
    onCloseClicked: (() -> Unit)?,
    onImageSelected: (ImageBitmap) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PhotoSelectionScreenViewModel = viewModel { PhotoSelectionScreenViewModel() },
) {
    val selectedImage = viewModel.selectedImage.collectAsState().value ?: images.first()

    GenericLayout(
        title = stringResource(resource = Res.string.photo_selection_title),
        onCloseClicked = onCloseClicked,
        actions = {
            IconButton(onClick = { onImageSelected(selectedImage) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        },
        mainContent = {
            SelectedImage(selectedImage = selectedImage)
        },
        additionalContent = {
            PhotoGallery(
                columns = 3,
                images = images,
                selectedImage = selectedImage,
                onImageSelected = { viewModel.onImageSelected(it) },
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun SelectedImage(
    selectedImage: ImageBitmap,
    modifier: Modifier = Modifier,
) {
    Crossfade(
        targetState = selectedImage,
        modifier = modifier,
    ) {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
