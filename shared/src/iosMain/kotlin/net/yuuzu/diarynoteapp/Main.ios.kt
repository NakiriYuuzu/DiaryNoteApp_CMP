package net.yuuzu.diarynoteapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import net.yuuzu.diarynoteapp.di.appModule
import net.yuuzu.diarynoteapp.di.initKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@Composable
fun MainView() {
    initKoin { modules(appModule) }
    val isDarkMode = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkMode,
        dynamicColor = false
    )
}

@Suppress("FunctionName", "unused")
fun MainViewController() = ComposeUIViewController { MainView() }