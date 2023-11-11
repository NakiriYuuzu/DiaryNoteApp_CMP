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
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import com.mohamedrejeb.calf.picker.toImageBitmap
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.viewModel
import net.yuuzu.diarynoteapp.presentation.detail.components.AddImage
import net.yuuzu.diarynoteapp.presentation.detail.components.ButtonWithText
import net.yuuzu.diarynoteapp.presentation.detail.components.TransparentTextField
import net.yuuzu.diarynoteapp.presentation.main.components.GraphicType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailScreen(
    diaryId: Long,
    onBackClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }

    val viewModel = viewModel(DetailViewModel::class) { DetailViewModel(diaryId) }
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pickerLauncher = rememberFilePickerLauncher(
        type = FilePickerFileType.Image,
        selectionMode = FilePickerSelectionMode.Single,
        onResult = { files ->
            files.firstOrNull()?.let { file ->
                viewModel.onEvent(DetailEvent.OnPhotoPicked(file.readByteArray()))
            }
        }
    )

    LaunchedEffect(state.validate) {
        if (state.message.isNotBlank()) {
            snackbarHostState.showSnackbar(
                message = state.message,
                duration = SnackbarDuration.Short
            )
        }
    }
    LaunchedEffect(state.completed) {
        if (state.completed) {
            onBackClicked()
        }
    }

    Scaffold(
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (diaryId > 0) {
                    FloatingActionButton(
                        onClick = { viewModel.onEvent(DetailEvent.OnDeleteClicked) }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete",
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                FloatingActionButton(
                    onClick = { viewModel.onEvent(DetailEvent.OnSaveClicked) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Save,
                        contentDescription = "Save",
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) { keyboardController?.hide() }
        ) {
            ImageArea(
                viewModel = viewModel,
                imagePicker = { pickerLauncher.launch() },
                onBackClicked = onBackClicked,
                modifier = Modifier.weight(0.4f)
            )
            InputArea(
                viewModel = viewModel,
                modifier = Modifier.weight(0.6f)
            )
        }
    }
}

@Composable
private fun ImageArea(
    viewModel: DetailViewModel,
    imagePicker: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        if (viewModel.newDiary?.imageBytes != null) {
            val imageBitmap = viewModel.newDiary?.imageBytes?.toImageBitmap()
            AddImage(
                graphicType = GraphicType.Image(
                    bitmap = imageBitmap ?: error("Image bitmap is null."),
                    imageScale = ContentScale.Crop,
                    imageQuality = FilterQuality.Medium
                ),
                onClicked = { imagePicker() },
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        } else {
            AddImage(
                graphicType = GraphicType.Icon(
                    imageVector = Icons.Rounded.Image,
                    tint = Color.Unspecified
                ),
                iconSize = 128.dp,
                onClicked = { imagePicker() },
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        }
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
            text = viewModel.newDiary?.tag ?: "",
            textColor = MaterialTheme.colorScheme.onPrimary,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.End
            ),
            hint = "Enter tag here",
            isHintVisible = viewModel.newDiary?.tag?.isEmpty() ?: true,
            onValueChanged = { viewModel.onEvent(DetailEvent.OnTagChanged(it)) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

@Composable
private fun InputArea(
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
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
                text = viewModel.newDiary?.title ?: "",
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
                hint = "Enter title here",
                isHintVisible = viewModel.newDiary?.title?.isEmpty() ?: true,
                onValueChanged = { viewModel.onEvent(DetailEvent.OnTitleChanged(it)) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                text = viewModel.newDiary?.content ?: "",
                onValueChanged = { viewModel.onEvent(DetailEvent.OnContentChanged(it)) },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp
                ),
                hint = "Enter content here",
                isHintVisible = viewModel.newDiary?.content?.isEmpty() ?: true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            )
        }
    }
}