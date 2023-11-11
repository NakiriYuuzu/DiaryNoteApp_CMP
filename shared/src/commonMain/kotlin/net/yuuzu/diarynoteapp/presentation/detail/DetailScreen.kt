package net.yuuzu.diarynoteapp.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.yuuzu.diarynoteapp.presentation.detail.components.AddImage
import net.yuuzu.diarynoteapp.presentation.detail.components.ButtonWithText
import net.yuuzu.diarynoteapp.presentation.detail.components.TransparentTextField
import net.yuuzu.diarynoteapp.presentation.main.components.GraphicType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailScreen(
    onBackClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FloatingActionButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Save,
                        contentDescription = null,
                    )
                }
            }
        },
        snackbarHost = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) { keyboardController?.hide() }
        ) {
            ImageArea(
                onBackClicked = onBackClicked,
                modifier = Modifier.weight(0.4f)
            )
            InputArea(
                modifier = Modifier.weight(0.6f)
            )
        }
    }
}

@Composable
private fun ImageArea(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tag by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        // TODO: 之後要改成從相簿中選擇圖片。
        AddImage(
            graphicType = GraphicType.Icon(
                imageVector = Icons.Rounded.Image,
                tint = Color.Unspecified
            ),
            iconSize = 128.dp,
            onClicked = {  },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        ButtonWithText(
            text = "Back",
            imageVector = Icons.Filled.KeyboardArrowLeft,
            textColor = MaterialTheme.colorScheme.onPrimary,
            iconColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .clickable { onBackClicked() }
                .padding(8.dp)
        )
        TransparentTextField(
            text = tag,
            textColor = MaterialTheme.colorScheme.onPrimary,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.End
            ),
            hint = "Enter tag here",
            isHintVisible = tag.isEmpty(),
            onValueChanged = { tag = it },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

@Composable
private fun InputArea(
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TransparentTextField(
                text = title,
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
                hint = "Enter title here",
                isHintVisible = title.isEmpty(),
                onValueChanged = { title = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                text = content,
                onValueChanged = { content = it },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp
                ),
                hint = "Enter content here",
                isHintVisible = content.isEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            )
        }
    }
}