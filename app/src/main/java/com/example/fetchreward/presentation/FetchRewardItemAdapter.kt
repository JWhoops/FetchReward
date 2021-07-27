package com.example.fetchreward.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchreward.R
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.databinding.FetchRewardListItemBinding


class FetchRewardItemAdapter : RecyclerView.Adapter<FetchRewardListItemViewHolder>() {
    private val fetchRewardItemList = mutableListOf<FetchRewardItem>()

    fun setList(data: List<FetchRewardItem>) {
        this.fetchRewardItemList.clear()
        this.fetchRewardItemList.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchRewardListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FetchRewardListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fetch_reward_list_item,
            parent,
            false
        )
        return FetchRewardListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return fetchRewardItemList.size
    }

    override fun onBindViewHolder(holder: FetchRewardListItemViewHolder, position: Int) {
        holder.bind(fetchRewardItemList[position])
    }
}

class FetchRewardListItemViewHolder(private val binding: FetchRewardListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fetchRewardItem: FetchRewardItem) {
        binding.listIdTextView.text = "${fetchRewardItem.listId}"
        binding.nameTextView.text = fetchRewardItem.name
    }

}