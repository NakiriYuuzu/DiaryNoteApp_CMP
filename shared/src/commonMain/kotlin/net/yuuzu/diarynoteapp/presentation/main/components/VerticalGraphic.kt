package net.yuuzu.diarynoteapp.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalGraphic(
    text: String,
    graphicType: GraphicType,
    contentDescription: String,
    size: Dp = 48.dp,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimary,
    padding: Dp = 8.dp,
    onClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable { onClicked() }
                .size(size)
                .padding(padding)
        ) {
            when (graphicType) {
                is GraphicType.Image -> {
                    Image(
                        bitmap = graphicType.bitmap,
                        contentDescription = contentDescription,
                        contentScale = graphicType.imageScale,
                        filterQuality = graphicType.imageQuality
                    )
                }
                is GraphicType.Icon -> {
                    Icon(
                        imageVector = graphicType.imageVector,
                        contentDescription = contentDescription,
                        tint = graphicType.tint,
                    )
                }
            }
        }
        Text(
            text = text,
            style = textStyle.copy(fontWeight = FontWeight.Medium),
            color = textColor,
        )
    }
}