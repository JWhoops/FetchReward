package com.example.fetchreward.common.di.app

import com.example.fetchreward.common.Constants
import com.example.fetchreward.data.network.FetchRewardApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object AppModule {

    // can also create a NetworkModule for two instance below
    @AppScope
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @AppScope
    @Provides
    fun fetchRewardApi(retrofit: Retrofit): FetchRewardApi =
        retrofit.create(FetchRewardApi::class.java)

}