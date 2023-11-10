package net.yuuzu.diarynoteapp.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomGraphic(
    imageBitmap: ImageBitmap? = null,
    imageVector: ImageVector? = Icons.Rounded.Person,
    description: String = "Person",
    shape: Shape = CircleShape,
    background: Color = MaterialTheme.colorScheme.onBackground,
    padding: Dp = 8.dp,
    size: Dp = 48.dp,
    onClicked: () -> Unit
) {
    if (imageBitmap != null) {
        // Render an Image if an ImageBitmap is provided
        Image(
            bitmap = imageBitmap,
            contentDescription = description,
            modifier = Modifier
                .clip(shape)
                .background(background)
                .clickable { onClicked() }
                .padding(padding)
                .size(size)
        )
    } else if (imageVector != null) {
        // Render an Icon if an ImageVector is provided
        Icon(
            imageVector = imageVector,
            contentDescription = description,
            modifier = Modifier
                .clip(shape)
                .background(background)
                .clickable { onClicked() }
                .padding(padding)
                .size(size)
        )
    }
}
