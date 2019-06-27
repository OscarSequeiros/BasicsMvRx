package com.example.basicsmvrx

import android.app.Application
import org.koin.core.context.startKoin

class BasicsMvRxApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            modules(matchModule)
        }
    }
}