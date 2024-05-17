package io.github.jerrymatera.projectsmanager

import android.app.Application
import io.github.jerrymatera.projectsmanager.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ProjectManager : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ProjectManager)
            modules(listOf(appModule))
        }
    }
}