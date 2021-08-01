package com.example.fetchreward.data

import android.util.Log
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.data.model.toItem
import com.example.fetchreward.data.network.FetchRewardApi
import javax.inject.Inject

class FetchRewardRepository @Inject constructor(
    private val fetchRewardApi: FetchRewardApi
) {

    suspend fun getFetchRewardItemsFromApi(): List<FetchRewardItem> {
        var fetchRewardList = listOf<FetchRewardItem>()
        try {
            val response = fetchRewardApi.getFetchRewardList()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                fetchRewardList = body.map { it.toItem() }
            }
        } catch (exception: Exception) {
            Log.e("FetchRewardApi", exception.message.toString())
        }
        return fetchRewardList
    }

}