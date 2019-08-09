package me.yangcx.demo.stickyheader.other

import android.app.Application
import me.yangcx.demo.stickyheader.di.GsonModule
import me.yangcx.demo.stickyheader.di.RepositoryModule
import me.yangcx.demo.stickyheader.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initSdk()
    }

    private fun initSdk() {
        startKoin {
            androidContext(this@DemoApplication)
            modules(
                listOf(
                    GsonModule.instance,
                    RepositoryModule.instance,
                    ViewModelModule.instance
                )
            )
        }
    }
}