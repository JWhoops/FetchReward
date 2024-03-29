package com.example.fetchreward.presentation.fetchrewardlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchreward.R
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.databinding.ActivityFetchRewardListBinding
import com.example.fetchreward.presentation.common.BaseActivity
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
        loadFetchRewardItemsList()
    }

    private fun loadFetchRewardItemsList() {
        fetchRewardListviewModel.getFetchRewardItems().observe(this, { fetchRewardListUiState ->
            if (fetchRewardListUiState != null) {
                render(fetchRewardListUiState)
            }
        })
    }

    private fun render(uiState: FetchRewardListUIState) {
        when (uiState) {
            is FetchRewardListUIState.Error   -> onListLoadError(uiState)
            FetchRewardListUIState.Loading    -> onListLoading()
            is FetchRewardListUIState.Success -> onListLoadSuccess(uiState)
        }
    }

    private fun onListLoadSuccess(uiState: FetchRewardListUIState.Success) {
        hideListProgressbar()
        setRecyclerViewData(uiState.fetchRewardItemsList)
    }

    private fun onListLoading() {
        showListProgressbar()
    }

    private fun onListLoadError(uiState: FetchRewardListUIState.Error) {
        hideListProgressbar()
        showNetworkError(uiState)
    }

    private fun initFetchRewardRecyclerView() {
        adapter = FetchRewardItemAdapter()
        binding.fetchRewardItemRecyclerView.let { view ->
            view.layoutManager = LinearLayoutManager(this)
            view.adapter = adapter
        }
    }

    private fun setRecyclerViewData(data: List<FetchRewardItem>) {
        adapter.setList(data)
        adapter.notifyDataSetChanged()
    }

    private fun showListProgressbar() {
        binding.fetchRewardListProgressBar.visibility = View.VISIBLE
    }

    private fun hideListProgressbar() {
        binding.fetchRewardListProgressBar.visibility = View.GONE
    }

    private fun showNetworkError(uiState: FetchRewardListUIState.Error) {
        Toast.makeText(this, uiState.message, Toast.LENGTH_LONG).show()
    }

}