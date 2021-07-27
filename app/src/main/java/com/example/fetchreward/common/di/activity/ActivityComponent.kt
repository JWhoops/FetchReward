package com.example.fetchreward.common.di.activity

import android.app.Activity
import com.example.fetchreward.presentation.fetchrewardlist.FetchRewardListActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(activity: FetchRewardListActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: Activity
        ): ActivityComponent
    }
}