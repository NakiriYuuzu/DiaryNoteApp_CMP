package net.yuuzu.diarynoteapp.android

import android.app.Application
import net.yuuzu.diarynoteapp.di.appModule
import net.yuuzu.diarynoteapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class DiaryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}