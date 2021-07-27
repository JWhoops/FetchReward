package com.example.fetchreward.presentation.fetchrewardlist

import com.example.fetchreward.data.model.FetchRewardItem

sealed class FetchRewardListUIState {
    object Loading : FetchRewardListUIState()
    data class Success(val fetchRewardItemsList: List<FetchRewardItem>) : FetchRewardListUIState()
    data class Error(val message: String) : FetchRewardListUIState()
}