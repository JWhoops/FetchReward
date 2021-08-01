package com.example.fetchreward.presentation.fetchrewardlist

import com.airbnb.mvrx.MavericksState
import com.example.fetchreward.data.model.FetchRewardItem


sealed class FetchRewardListUIState {
    object Loading : FetchRewardListUIState()
    data class Success(val fetchRewardItemsList: List<FetchRewardItem>) : FetchRewardListUIState()
    data class Error(val message: String) : FetchRewardListUIState()
}

data class FetchRewardListState(
    val fetchRewardListUIState: FetchRewardListUIState = FetchRewardListUIState.Loading
) : MavericksState