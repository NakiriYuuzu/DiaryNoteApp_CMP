package net.yuuzu.diarynoteapp.presentation.main

import net.yuuzu.diarynoteapp.data.model.Diary
import net.yuuzu.diarynoteapp.data.remote.WeatherResponse

data class MainState(
    val tags: List<String> = emptyList(),
    val diaries: List<Diary> = emptyList(),
    val recentDiaries: List<Diary> = emptyList(),
    val weather: WeatherResponse? = null,

    val selectedTag: String = "All",
    val selectedDate: Long = 0,
    val selectedDiary: Long = 0,

    val isCalendarDialogOpen: Boolean = false,
)