package com.example.fetchreward.domain

import com.example.fetchreward.data.FetchRewardRepository
import com.example.fetchreward.data.model.FetchRewardItem
import javax.inject.Inject

class GetFetchRewardItemsUseCase @Inject constructor(
    private val fetchRewardRepository: FetchRewardRepository
) {
    suspend fun execute(): List<FetchRewardItem> = fetchRewardRepository.getFetchRewardItemsFromApi()
}