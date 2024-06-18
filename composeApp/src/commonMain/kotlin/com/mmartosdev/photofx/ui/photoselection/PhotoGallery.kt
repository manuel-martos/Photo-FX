package com.mmartosdev.photofx.ui.photoselection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mmartosdev.photofx.components.SelectionIndicator
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import photo_fx.composeapp.generated.resources.Res
import photo_fx.composeapp.generated.resources.photo_gallery_description

@Composable
fun PhotoGallery(
    columns: Int,
    images: ImmutableList<ImageBitmap>,
    selectedImage: ImageBitmap,
    onImageSelected: (ImageBitmap) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        horizontalArrangement = spacedBy(2.dp),
        verticalArrangement = spacedBy(2.dp),
        modifier = modifier,
    ) {
        items(items = images) { imageBitmap ->
            PhotoItem(
                image = imageBitmap,
                isSelected = selectedImage == imageBitmap,
                onPhotoSelected = onImageSelected,
                modifier = Modifier.aspectRatio(16f / 9f)
            )
        }
    }
}

@Composable
fun PhotoItem(
    image: ImageBitmap,
    isSelected: Boolean,
    onPhotoSelected: (ImageBitmap) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable { onPhotoSelected(image) }
    ) {
        Image(
            bitmap = image,
            contentDescription = stringResource(Res.string.photo_gallery_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
                .size(18.dp)
        ) {
            SelectionIndicator()
        }
    }
}
