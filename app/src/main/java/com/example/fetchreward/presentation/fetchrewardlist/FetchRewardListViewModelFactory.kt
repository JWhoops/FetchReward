package com.example.fetchreward.presentation.fetchrewardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchreward.common.di.activity.ActivityScope
import com.example.fetchreward.domain.GetFetchRewardItemsUseCase
import javax.inject.Inject

@ActivityScope
class FetchRewardListViewModelFactory @Inject constructor(
    private val getFetchRewardItemsUseCase: GetFetchRewardItemsUseCase,
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FetchRewardListViewModel(
            getFetchRewardItemsUseCase,
        ) as T
    }
}