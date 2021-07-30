package com.example.fetchreward.common.di

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    // Help Hilt discover AppcompatActivity since we can't pass bootstrapping dependency into module
    // since we only use AppCompatActivity, it should be good to cast activity
    @Provides
    fun appCompatActivity(activity: Activity) = activity as AppCompatActivity

    // demonstration purpose for setting up ActivityModule
    @Provides
    fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

}