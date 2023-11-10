package net.yuuzu.diarynoteapp.common.navigation

sealed class Screen(
    val route: String,
    val objectName: String? = null,
    val objectPath: String? = null
) {
    data object MainScreen: Screen(
        route = "main_screen"
    )
    data object DetailScreen: Screen(
        route = "detail_screen",
        objectName = "diaryId",
        objectPath = "/{diaryId}"
    )
}