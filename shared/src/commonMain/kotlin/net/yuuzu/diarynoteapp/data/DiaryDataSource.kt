package net.yuuzu.diarynoteapp.data

import kotlinx.coroutines.flow.Flow
import net.yuuzu.diarynoteapp.data.model.Diary
import net.yuuzu.diarynoteapp.data.remote.WeatherResponse

interface DiaryDataSource {
    interface Local {
        fun getDiary(id: Long): Flow<Diary>
        fun getDiaries(): Flow<List<Diary>>
        fun getRecentDiaries(amount: Long): Flow<List<Diary>>
        fun getDiariesByTag(tag: String): Flow<List<Diary>>
        fun getDiariesByDate(startDate: Long, endDate: Long): Flow<List<Diary>>
        suspend fun insertDiary(diary: Diary)
        suspend fun deleteDiary(id: Long)
    }
    interface Remote {
        suspend fun getWeather(lat: Double, lon: Double): WeatherResponse
    }
}