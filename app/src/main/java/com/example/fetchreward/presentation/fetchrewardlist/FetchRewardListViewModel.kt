package com.example.fetchreward.presentation.fetchrewardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fetchreward.domain.GetFetchRewardItemsUseCase

class FetchRewardListViewModel(
    private val getFetchRewardItemsUseCase: GetFetchRewardItemsUseCase
) : ViewModel() {

    fun getFetchRewardItems() = liveData {
        var fetchRewardItemList = getFetchRewardItemsUseCase.execute()

        fetchRewardItemList = fetchRewardItemList
            .filter { !it.name.isNullOrEmpty() }
            .sortedWith(compareBy({ it.listId }, { it.name }))

        emit(fetchRewardItemList)
    }

}