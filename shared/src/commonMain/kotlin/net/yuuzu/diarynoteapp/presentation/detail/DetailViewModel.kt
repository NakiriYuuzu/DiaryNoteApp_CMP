package net.yuuzu.diarynoteapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import net.yuuzu.diarynoteapp.data.DiaryDataSource
import net.yuuzu.diarynoteapp.data.model.Diary
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailViewModel(
    private val diaryId: Long
): ViewModel(), KoinComponent {
    private val entity: DiaryDataSource.Local by inject()

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    var newDiary: Diary? by mutableStateOf(null)
        private set

    init {
        if (diaryId > 0 && !state.value.completed) {
            viewModelScope.launch {
                entity.getDiary(diaryId).collect { diary ->
                    newDiary = diary
                }
            }
        } else {
            newDiary = Diary.empty
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnTagChanged -> {
                newDiary = newDiary?.copy(
                    tag = event.value
                )
            }
            is DetailEvent.OnTitleChanged -> {
                newDiary = newDiary?.copy(
                    title = event.value
                )
            }
            is DetailEvent.OnContentChanged -> {
                newDiary = newDiary?.copy(
                    content = event.value
                )
            }
            is DetailEvent.OnPhotoPicked -> {
                newDiary = newDiary?.copy(
                    imageBytes = event.value
                )
            }
            DetailEvent.OnDeleteClicked -> {
                _state.update {
                    it.copy(
                        completed = true
                    )
                }
                newDiary = null
                viewModelScope.launch { entity.deleteDiary(diaryId) }
            }
            DetailEvent.OnSaveClicked -> {
                newDiary?.let { diary ->
                    if (diary.title.isBlank()) {
                        _state.update { it.copy(
                            validate = !it.validate,
                            message = "Title cannot be empty"
                        ) }
                        return
                    }
                    if (diary.content.isBlank()) {
                        _state.update { it.copy(
                            validate = !it.validate,
                            message = "Content cannot be empty"
                        ) }
                        return
                    }
                    if (diary.tag.isBlank()) {
                        _state.update { it.copy(
                            validate = !it.validate,
                            message = "Tag cannot be empty"
                        ) }
                        return
                    }
                    viewModelScope.launch {
                        entity.insertDiary(diary)
                        newDiary = null
                        _state.update { it.copy(
                            validate = false,
                            completed = true
                        ) }
                    }
                }
            }
        }
    }
}