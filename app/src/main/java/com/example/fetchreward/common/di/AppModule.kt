package com.example.fetchreward.common.di

import com.example.fetchreward.common.Constants
import com.example.fetchreward.data.network.FetchRewardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun fetchRewardApi(retrofit: Retrofit): FetchRewardApi =
        retrofit.create(FetchRewardApi::class.java)

}