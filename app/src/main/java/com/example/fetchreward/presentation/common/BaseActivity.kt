package com.example.fetchreward.presentation.common

import androidx.appcompat.app.AppCompatActivity
import com.example.fetchreward.common.CustomApplication

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as CustomApplication).appComponent

    private val activityComponent by lazy {
        appComponent.newActivityComponent().create(
            this
        )
    }

    protected val injector get() = activityComponent
}