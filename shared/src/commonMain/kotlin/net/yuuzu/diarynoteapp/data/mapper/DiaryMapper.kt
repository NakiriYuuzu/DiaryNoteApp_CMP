package net.yuuzu.diarynoteapp.data.mapper

import net.yuuzu.diarynoteapp.data.model.Diary
import net.yuuzu.diarynoteapp.database.DiaryEntity
import net.yuuzu.diarynoteapp.utils.ImageStorage

suspend fun DiaryEntity.toDiary(imageStorage: ImageStorage): Diary {
    return Diary(
        id = id,
        tag = tag,
        title = title,
        content = content,
        imageBytes = imagePath?.let { imageStorage.getImage(it) },
        date = createdAt
    )
}