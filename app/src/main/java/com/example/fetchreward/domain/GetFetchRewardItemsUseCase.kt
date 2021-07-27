package com.example.fetchreward.domain

import com.example.fetchreward.data.FetchRewardRepository
import com.example.fetchreward.data.model.FetchRewardItem

class GetFetchRewardItemsUseCase {
    suspend fun execute(): List<FetchRewardItem> = FetchRewardRepository.getFetchRewardItemsFromApi()
}