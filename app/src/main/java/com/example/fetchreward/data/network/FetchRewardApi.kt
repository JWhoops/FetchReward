package com.example.fetchreward.data.network

import com.example.fetchreward.data.model.FetchRewardResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchRewardApi {

    @GET("/hiring.json")
    suspend fun getFetchRewardList(): Response<List<FetchRewardResponse>>

}