package net.yuuzu.diarynoteapp.common.navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import net.yuuzu.diarynoteapp.presentation.detail.DetailScreen
import net.yuuzu.diarynoteapp.presentation.main.MainScreen

@Composable
fun Navigation(navigator: Navigator = rememberNavigator()) {
    NavHost(
        navigator = navigator,
        initialRoute = Screen.MainScreen.route, // 一開始要顯示的畫面。
    ) {
        scene(route = Screen.MainScreen.route) {
            MainScreen()
        }
        scene(route = Screen.DetailScreen.route.plus(Screen.DetailScreen.objectPath)) {
            DetailScreen()
        }
    }
}