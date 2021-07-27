package com.example.fetchreward.presentation.fetchrewardlist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchreward.R
import com.example.fetchreward.presentation.common.BaseActivity
import com.example.fetchreward.databinding.ActivityFetchRewardListBinding
import javax.inject.Inject

class FetchRewardListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: FetchRewardListViewModelFactory

    private lateinit var fetchRewardListviewModel: FetchRewardListViewModel
    private lateinit var binding: ActivityFetchRewardListBinding
    private lateinit var adapter: FetchRewardItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fetch_reward_list)
        injector.inject(this)
        fetchRewardListviewModel = ViewModelProvider(this, viewModelFactory).get(FetchRewardListViewModel::class.java)

        initFetchRewardRecyclerView()
        displayFetchRewardItems()
    }

    private fun displayFetchRewardItems() {
        val responseLiveData = fetchRewardListviewModel.getFetchRewardItems()
        responseLiveData.observe(this, { filteredData ->
            adapter.setList(filteredData)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initFetchRewardRecyclerView() {
        adapter = FetchRewardItemAdapter()
        binding.fetchRewardItemRecyclerView.let { view ->
            view.layoutManager = LinearLayoutManager(this)
            view.adapter = adapter
        }
    }
}