package net.yuuzu.diarynoteapp.data.model

data class Diary(
    val id: Long?,
    val tag: String,
    val title: String,
    val content: String,
    val imageBytes: ByteArray?,
    val date: Long?
) {
    companion object {
        val empty = Diary(
            id = null,
            tag = "",
            title = "",
            content = "",
            imageBytes = null,
            date = null
        )
    }
}
