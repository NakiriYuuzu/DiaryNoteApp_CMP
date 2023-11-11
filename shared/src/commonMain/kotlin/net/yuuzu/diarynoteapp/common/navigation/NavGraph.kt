package net.yuuzu.diarynoteapp.common.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import net.yuuzu.diarynoteapp.presentation.detail.DetailScreen
import net.yuuzu.diarynoteapp.presentation.main.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navigator: Navigator = rememberNavigator()
) {
    val bottomSheetState = rememberBottomSheetScaffoldState() // TODO: 之後要放在 View Model 中以避免重建。
    NavHost(
        navigator = navigator,
        initialRoute = Screen.MainScreen.route, // 一開始要顯示的畫面。
    ) {
        scene(route = Screen.MainScreen.route) {
            MainScreen(
                bottomSheetState = bottomSheetState,
                addDiaryOnClicked = { navigator.navigate(Screen.DetailScreen.route) }
            )
        }
        scene(route = Screen.DetailScreen.route) {
            DetailScreen(onBackClicked = { navigator.popBackStack() })
        }
    }
}