package com.example.fetchreward.data

import android.util.Log
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.data.network.FetchRewardNetwork

object FetchRewardRepository {

    suspend fun getFetchRewardItemsFromApi(): List<FetchRewardItem> {
        val fetchRewardList = listOf<FetchRewardItem>()
        try {
            val response = FetchRewardNetwork.getFetchRewardList()
            val body = response.body()
            return if (response.isSuccessful && body != null) {
                body
            } else {
                fetchRewardList
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return fetchRewardList
    }

}