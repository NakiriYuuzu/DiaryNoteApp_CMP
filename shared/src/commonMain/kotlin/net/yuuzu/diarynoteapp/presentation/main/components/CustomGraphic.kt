package net.yuuzu.diarynoteapp.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomGraphic(
    graphicType: GraphicType,
    description: String = "Person",
    shape: Shape = CircleShape,
    background: Color = MaterialTheme.colorScheme.onBackground,
    padding: Dp = 8.dp,
    size: Dp = 48.dp,
    onClicked: () -> Unit
) {
    when (graphicType) {
        is GraphicType.Icon -> {
            Icon(
                imageVector = graphicType.imageVector,
                contentDescription = description,
                tint = graphicType.tint,
                modifier = Modifier
                    .clip(shape)
                    .background(background)
                    .clickable { onClicked() }
                    .padding(padding)
                    .size(size)
            )

        }
        is GraphicType.Image -> {
            Image(
                bitmap = graphicType.bitmap,
                contentScale = graphicType.imageScale,
                filterQuality = graphicType.imageQuality,
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
}

sealed class GraphicType {
    data class Image(
        val bitmap: ImageBitmap,
        val imageScale: ContentScale = ContentScale.Crop,
        val imageQuality: FilterQuality = FilterQuality.None
    ) : GraphicType()

    data class Icon(
        val imageVector: ImageVector,
        val tint: Color = Color.Unspecified,
    ) : GraphicType()
}
