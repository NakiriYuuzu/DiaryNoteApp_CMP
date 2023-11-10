package net.yuuzu.diarynoteapp.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopHeader(
    name: String,
    weather: String,
    profileImage: ImageBitmap?,
    padding: Dp = 16.dp,
    background: Color = MaterialTheme.colorScheme.primary,
    onProfileClicked: () -> Unit,
    options: (@Composable () -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .padding(padding)
    ) {
        if (profileImage != null) {
            // TODO: Profile Image
        } else {
            CustomGraphic(onClicked = onProfileClicked)
        }

        Column(
            modifier = Modifier
                .padding(start = padding)
                .weight(1f)
        ) {
            Text(
                text = weather,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        options?.invoke()
    }
}