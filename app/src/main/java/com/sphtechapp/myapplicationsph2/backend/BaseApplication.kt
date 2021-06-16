package com.sphtechapp.myapplicationsph2.backend

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.sphtechapp.myapplicationsph2.activities.main.di.itemModule
import com.sphtechapp.myapplicationsph2.di.retrofitModule

class BaseApplication : Application() {

    companion object {

        private lateinit var context: Context

        @JvmStatic
        fun init(context: Context) {
            Companion.context = context
        }

        @JvmStatic
        fun getContext(): Context = context

    }

    override fun onCreate() {
        super.onCreate()
        init(applicationContext)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(retrofitModule, itemModule))
        }
    }

}