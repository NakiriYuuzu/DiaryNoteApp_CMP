package net.yuuzu.diarynoteapp.presentation.main

import net.yuuzu.diarynoteapp.data.model.Diary

data class MainState(
    val tags: List<String> = emptyList(),
    val diaries: List<Diary> = emptyList(),
    val recentDiaries: List<Diary> = emptyList(),

    val selectedTag: String = "All",
    val selectedDate: Long = 0,
    val selectedDiary: Long = 0,

    val isCalendarDialogOpen: Boolean = false,
)