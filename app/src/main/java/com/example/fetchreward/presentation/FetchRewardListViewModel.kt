package com.example.fetchreward.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fetchreward.domain.GetFetchRewardItemsUseCase

class FetchRewardListViewModel : ViewModel() {
    private val getFetchRewardItemsUseCase: GetFetchRewardItemsUseCase = GetFetchRewardItemsUseCase()

    fun getFetchRewardItems() = liveData {
        var fetchRewardItemList = getFetchRewardItemsUseCase.execute()

        fetchRewardItemList = fetchRewardItemList
            .filter { !it.name.isNullOrEmpty() }
            .sortedWith(compareBy({ it.listId }, { it.name }))

        emit(fetchRewardItemList)
    }

}