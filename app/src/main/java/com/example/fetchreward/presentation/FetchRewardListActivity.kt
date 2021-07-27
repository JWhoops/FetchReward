package com.example.fetchreward.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchreward.R
import com.example.fetchreward.databinding.ActivityFetchRewardListBinding

class FetchRewardListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFetchRewardListBinding
    private lateinit var adapter: FetchRewardItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fetch_reward_list)
        val viewModel: FetchRewardListViewModel by viewModels()

        adapter = FetchRewardItemAdapter()
        binding.fetchRewardItemRecyclerView.let { view ->
            view.layoutManager = LinearLayoutManager(this)
            view.adapter = adapter
        }

        val responseLiveData = viewModel.getFetchRewardItems()
        responseLiveData.observe(this, { filteredData ->
            adapter.apply {
                setList(filteredData)
                notifyDataSetChanged()
            }
        })
    }
}