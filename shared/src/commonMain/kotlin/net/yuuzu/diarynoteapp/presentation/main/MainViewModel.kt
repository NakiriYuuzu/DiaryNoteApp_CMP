package net.yuuzu.diarynoteapp.presentation.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import net.yuuzu.diarynoteapp.data.DiaryDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel: ViewModel(), KoinComponent {
    private val entity: DiaryDataSource.Local by inject()

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        updateDiaries()
        updateRecentDiaries()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.OnCalendarClicked -> {
                if (_state.value.isCalendarDialogOpen) {
                    _state.update { it.copy(
                        isCalendarDialogOpen = false
                    ) }
                    return
                }

                _state.update { it.copy(
                    isCalendarDialogOpen = true
                ) }
            }
            is MainEvent.OnCalendarSelected -> {
                val startDate = event.value
                val endDate = event.value + 86400000L

                viewModelScope.launch {
                    entity.getDiariesByDate(startDate, endDate).collect { diaries ->
                        _state.update { it.copy(
                            selectedDate = event.value,
                            diaries = diaries,
                            isCalendarDialogOpen = false
                        ) }
                    }
                }
            }
            is MainEvent.OnTagSelected -> {
                if (event.tag == "All") {
                    updateDiaries()
                    return
                }

                viewModelScope.launch {
                    entity.getDiariesByTag(event.tag).collect { diaries ->
                        _state.update { it.copy(
                            diaries = diaries
                        ) }
                    }
                }
            }
        }
    }

    private fun updateDiaries() {
        viewModelScope.launch {
            entity.getDiaries().collect { diaries ->
                _state.update { mainState ->
                    mainState.copy(
                        diaries = diaries,
                        tags = diaries
                            .map { it.tag.uppercase() }
                            .toMutableList()
                            .apply { add(0, "All") }
                            .toHashSet()
                            .toList()
                    )
                }
            }
        }
    }

    private fun updateRecentDiaries() {
        viewModelScope.launch {
            entity.getRecentDiaries(10).collect { diaries ->
                _state.update {
                    it.copy(
                        recentDiaries = diaries
                    )
                }
            }
        }
    }
}