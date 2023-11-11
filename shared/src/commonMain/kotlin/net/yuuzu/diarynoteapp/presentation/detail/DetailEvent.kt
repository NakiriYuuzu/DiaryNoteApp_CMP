package net.yuuzu.diarynoteapp.presentation.detail

sealed interface DetailEvent {
    data class OnTitleChanged(val value: String) : DetailEvent
    data class OnContentChanged(val value: String) : DetailEvent
    data class OnTagChanged(val value: String) : DetailEvent
    class OnPhotoPicked(val value: ByteArray) : DetailEvent

    data object OnSaveClicked : DetailEvent
    data object OnDeleteClicked : DetailEvent
}