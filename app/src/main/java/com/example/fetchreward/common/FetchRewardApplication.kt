package com.example.fetchreward.common

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FetchRewardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}