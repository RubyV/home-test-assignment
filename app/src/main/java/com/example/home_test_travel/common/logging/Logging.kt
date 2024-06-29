package com.example.home_test_travel.common.logging

import timber.log.Timber

object Logging {

    fun init() {
        Timber.plant(Timber.DebugTree())
    }
}
