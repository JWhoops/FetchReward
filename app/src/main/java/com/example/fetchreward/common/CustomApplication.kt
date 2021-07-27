package com.example.fetchreward.common

import android.app.Application
import com.example.fetchreward.common.di.app.AppComponent
import com.example.fetchreward.common.di.app.DaggerAppComponent

class CustomApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }
}