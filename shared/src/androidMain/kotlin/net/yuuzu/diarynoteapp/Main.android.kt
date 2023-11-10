package net.yuuzu.diarynoteapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun MainView() {
    App(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = false
    )
}