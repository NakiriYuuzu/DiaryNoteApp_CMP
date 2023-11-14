package net.yuuzu.diarynoteapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.moriatsushi.insetsx.SystemBarsBehavior
import com.moriatsushi.insetsx.rememberWindowInsetsController
import moe.tlaster.precompose.PreComposeApp
import net.yuuzu.diarynoteapp.common.navigation.Navigation
import net.yuuzu.diarynoteapp.common.theme.AppTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    val windowInsets = rememberWindowInsetsController()

    LaunchedEffect(Unit) {
        windowInsets?.setIsNavigationBarsVisible(false)
        windowInsets?.setNavigationBarsContentColor(darkTheme)
        windowInsets?.setIsStatusBarsVisible(true)
        windowInsets?.setStatusBarContentColor(darkTheme)
        windowInsets?.setSystemBarsBehavior(SystemBarsBehavior.Default)
    }

    PreComposeApp {
        AppTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor
        ) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Navigation()
            }
        }
    }
}