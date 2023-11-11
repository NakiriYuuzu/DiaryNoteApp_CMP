package net.yuuzu.diarynoteapp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import net.yuuzu.diarynoteapp.database.DiaryDatabase
import net.yuuzu.diarynoteapp.utils.ImageStorage
import net.yuuzu.diarynoteapp.utils.ImageStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = DiaryDatabase.Schema,
            context = androidContext(),
            name = "diary.db"
        )
    }
    single<ImageStorage> { ImageStorageImpl(androidContext()) }
}