package com.example.fetchreward.presentation.fetchrewardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.domain.GetFetchRewardItemsUseCase

class FetchRewardListViewModel(
    private val getFetchRewardItemsUseCase: GetFetchRewardItemsUseCase
) : ViewModel() {

    /*
       if we want to make list data survive config change, we can easily
       create a class instance livedata that holds UiState and fetch it
       whenever we need it
    */

    fun getFetchRewardItems() = liveData {
        emit(FetchRewardListUIState.Loading)
        try {
            var fetchRewardItemList = getFetchRewardItemsUseCase.execute()
            if (fetchRewardItemList.isNotEmpty()) {
                fetchRewardItemList = filterFetchRewardListByName(fetchRewardItemList)
                fetchRewardItemList = sortFetchRewardListByListIdThenName(fetchRewardItemList)
                emit(FetchRewardListUIState.Success(fetchRewardItemList))
            } else {
                emit(FetchRewardListUIState.Error("Network Request failed!"))
            }
        } catch (exception: Exception) {
            emit(FetchRewardListUIState.Error("Network Request failed!"))
        }
    }

    fun filterFetchRewardListByName(fetchRewardItemList: List<FetchRewardItem>): List<FetchRewardItem> =
        fetchRewardItemList.filter { !it.name.isNullOrEmpty() }

    fun sortFetchRewardListByListIdThenName(fetchRewardItemList: List<FetchRewardItem>): List<FetchRewardItem> =
        // Name: Not sure if I should sort with number in name field or just name as a str?
        fetchRewardItemList.sortedWith(compareBy({ it.listId }, { it.name }))

}