package com.example.fetchreward.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FetchRewardNetwork {

    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val fetchRewardApi = retrofit.create(FetchRewardApi::class.java)

    suspend fun getFetchRewardList() = fetchRewardApi.getFetchRewardList()

}