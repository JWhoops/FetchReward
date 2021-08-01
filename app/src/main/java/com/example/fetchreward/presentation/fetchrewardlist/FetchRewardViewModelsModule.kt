package com.airbnb.mvrx.hellohilt

import com.example.fetchreward.common.di.mavericks.AssistedViewModelFactory
import com.example.fetchreward.common.di.mavericks.MavericksViewModelComponent
import com.example.fetchreward.common.di.mavericks.ViewModelKey
import com.example.fetchreward.presentation.fetchrewardlist.FetchRewardListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface FetchRewardViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(FetchRewardListViewModel::class)
    fun fetchRewardListViewModelFactory(factory: FetchRewardListViewModel.Factory): AssistedViewModelFactory<*, *>
}
