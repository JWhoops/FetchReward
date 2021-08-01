package com.example.fetchreward.data.model

data class FetchRewardResponse(
    val id: Int,
    val listId: Int,
    val name: String?
)

data class FetchRewardItem(
    val id: Int,
    val listId: Int,
    val name: String
)

fun FetchRewardResponse.toItem(): FetchRewardItem = FetchRewardItem(id, listId, name ?: "")