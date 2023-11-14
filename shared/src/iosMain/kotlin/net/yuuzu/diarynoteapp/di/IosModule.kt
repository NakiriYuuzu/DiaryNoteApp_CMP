package net.yuuzu.diarynoteapp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import net.yuuzu.diarynoteapp.database.DiaryDatabase
import net.yuuzu.diarynoteapp.utils.ImageStorage
import net.yuuzu.diarynoteapp.utils.ImageStorageImpl
import org.koin.dsl.module

val appModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(DiaryDatabase.Schema, "diary.db")
    }
    single<ImageStorage> { ImageStorageImpl() }
}