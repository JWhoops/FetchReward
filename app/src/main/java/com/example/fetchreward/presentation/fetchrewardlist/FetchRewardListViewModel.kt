package com.example.fetchreward.presentation.fetchrewardlist

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.example.fetchreward.common.di.mavericks.AssistedViewModelFactory
import com.example.fetchreward.common.di.mavericks.hiltMavericksViewModelFactory
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.domain.GetFetchRewardItemsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class FetchRewardListViewModel @AssistedInject constructor(
    @Assisted state: FetchRewardListState,
    private val getFetchRewardItemsUseCase: GetFetchRewardItemsUseCase
) : MavericksViewModel<FetchRewardListState>(state) {

    /*
       if we want to make list data survive config change, we can easily
       create a class instance livedata that holds UiState and fetch it
       whenever we need it
    */
    init {
        viewModelScope.launch {
            loadFetchRewardList()
        }
    }

    private suspend fun loadFetchRewardList() {
        try {
            var fetchRewardItemList = getFetchRewardItemsUseCase.execute()
            if (fetchRewardItemList.isNotEmpty()) {
                fetchRewardItemList = filterFetchRewardListByName(fetchRewardItemList)
                fetchRewardItemList = sortFetchRewardListByListIdThenName(fetchRewardItemList)
                setState { copy(fetchRewardListUIState = FetchRewardListUIState.Success(fetchRewardItemList)) }
            } else {
                setState { copy(fetchRewardListUIState = FetchRewardListUIState.Error("Network Request failed!")) }
            }
        } catch (exception: Exception) {
            setState { copy(fetchRewardListUIState = FetchRewardListUIState.Error("Network Request failed!")) }
        }
    }

    fun filterFetchRewardListByName(fetchRewardItemList: List<FetchRewardItem>): List<FetchRewardItem> =
        fetchRewardItemList.filter { it.name.isNotEmpty() }

    fun sortFetchRewardListByListIdThenName(fetchRewardItemList: List<FetchRewardItem>): List<FetchRewardItem> =
        // Name: Not sure if I should sort with number in name field or just name as a str?
        fetchRewardItemList.sortedWith(compareBy({ it.listId }, { it.name }))

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<FetchRewardListViewModel, FetchRewardListState> {
        override fun create(state: FetchRewardListState): FetchRewardListViewModel
    }

    companion object : MavericksViewModelFactory<FetchRewardListViewModel, FetchRewardListState> by hiltMavericksViewModelFactory()
}