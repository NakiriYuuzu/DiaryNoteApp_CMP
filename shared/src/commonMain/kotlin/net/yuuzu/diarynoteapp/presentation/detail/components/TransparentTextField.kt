package net.yuuzu.diarynoteapp.presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TransparentTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    textStyle: TextStyle = TextStyle(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    singleLine: Boolean = false,
    onFocusChanged: (FocusState) -> Unit = {}
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChanged(it)
                }
        )
        if(isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = textColor.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}