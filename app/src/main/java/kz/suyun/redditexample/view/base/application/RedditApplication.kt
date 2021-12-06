package kz.suyun.redditexample.view.base.application

import android.app.Application
import kz.suyun.redditexample.di.apiModule
import kz.suyun.redditexample.di.repositoryModule
import kz.suyun.redditexample.di.retrofitModule
import kz.suyun.redditexample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RedditApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RedditApplication)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}