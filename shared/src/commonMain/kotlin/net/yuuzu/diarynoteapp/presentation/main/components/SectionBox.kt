package net.yuuzu.diarynoteapp.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SectionBox(
    title: String? = null,
    shapes: CornerBasedShape = MaterialTheme.shapes.large,
    background: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shapes)
            .background(background)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
            }
        }
        content?.invoke()
    }
}