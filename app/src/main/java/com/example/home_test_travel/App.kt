package com.example.home_test_travel

import android.app.Application
import com.example.home_test_travel.common.logging.Logging
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        Logging.init()
    }
}