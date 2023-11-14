package net.yuuzu.diarynoteapp.di

import net.yuuzu.diarynoteapp.data.DiaryDataSource
import net.yuuzu.diarynoteapp.data.local.DiaryLocalDataSourceImpl
import net.yuuzu.diarynoteapp.data.remote.ApiService
import net.yuuzu.diarynoteapp.data.remote.DiaryRemoteDataSourceImpl
import net.yuuzu.diarynoteapp.database.DiaryDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val dataModule = module {
    single<DiaryDataSource.Local> {
        DiaryLocalDataSourceImpl(
            database = DiaryDatabase(driver = get()),
            imageStorage = get()
        )
    }
    single { ApiService() }
    single<DiaryDataSource.Remote> { DiaryRemoteDataSourceImpl(get()) }
}

private val sharedModules = listOf(
    dataModule
)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(sharedModules)
}