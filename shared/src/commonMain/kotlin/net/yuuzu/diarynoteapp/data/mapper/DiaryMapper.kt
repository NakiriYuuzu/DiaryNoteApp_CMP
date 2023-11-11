package net.yuuzu.diarynoteapp.data.mapper

import net.yuuzu.diarynoteapp.data.model.Diary
import net.yuuzu.diarynoteapp.database.DiaryEntity

fun DiaryEntity.toDiary(): Diary {
    return Diary(
        id = id,
        tag = tag,
        title = title,
        content = content,
        imageBytes = null,
        date = createdAt
    )
}