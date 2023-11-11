package net.yuuzu.diarynoteapp.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock
import net.yuuzu.diarynoteapp.data.DiaryDataSource
import net.yuuzu.diarynoteapp.data.mapper.toDiary
import net.yuuzu.diarynoteapp.data.model.Diary
import net.yuuzu.diarynoteapp.database.DiaryDatabase

class DiaryLocalDataSourceImpl(
    database: DiaryDatabase,
): DiaryDataSource.Local {
    private val queries = database.diaryQueries

    override fun getDiary(id: Long): Flow<Diary> {
        return queries
            .getDiary(id)
            .asFlow()
            .map { diaryEntity ->
                diaryEntity.executeAsOne().toDiary()
            }
    }

    override fun getDiaries(): Flow<List<Diary>> {
        return queries
            .getDiaries()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary() }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getRecentDiaries(amount: Long): Flow<List<Diary>> {
        return queries
            .getRecentDiaries(amount)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary() }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getDiariesByTag(tag: String): Flow<List<Diary>> {
        return queries
            .getDiariesByTag(tag = tag)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary() }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getDiariesByDate(startDate: Long, endDate: Long): Flow<List<Diary>> {
        return queries
            .getDiariesByDate(startDate = startDate, endDate = endDate)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary() }
                        }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertDiary(diary: Diary) {
        queries.insertDiaryEntity(
            id = diary.id,
            tag = diary.tag,
            title = diary.title,
            content = diary.content,
            imagePath = null,
            createdAt = Clock.System.now().toEpochMilliseconds(),
        )
    }

    override suspend fun deleteDiary(id: Long) {
        queries.deleteDiary(id = id)
    }
}