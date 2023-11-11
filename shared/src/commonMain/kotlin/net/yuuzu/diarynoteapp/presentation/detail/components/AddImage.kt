package net.yuuzu.diarynoteapp.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.yuuzu.diarynoteapp.presentation.main.components.GraphicType

@Composable
fun AddImage(
    graphicType: GraphicType,
    iconSize: Dp = 48.dp,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        when (graphicType) {
            is GraphicType.Image -> {
                Image(
                    bitmap = graphicType.bitmap,
                    contentDescription = null,
                    contentScale = graphicType.imageScale,
                    filterQuality = graphicType.imageQuality,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClicked() }
                )
            }
            is GraphicType.Icon -> {
                Icon(
                    imageVector = graphicType.imageVector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .clickable { onClicked() }
                        .padding(8.dp)
                )
            }
        }
    }
}