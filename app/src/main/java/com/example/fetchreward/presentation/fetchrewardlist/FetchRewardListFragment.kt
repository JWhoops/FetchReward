package com.example.fetchreward.presentation.fetchrewardlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.fetchreward.R
import com.example.fetchreward.data.model.FetchRewardItem
import com.example.fetchreward.databinding.ActivityFetchRewardListBinding

class FetchRewardListFragment : Fragment(), MavericksView {

    private val fetchRewardListviewModel: FetchRewardListViewModel by fragmentViewModel()
    private lateinit var binding: ActivityFetchRewardListBinding
    private lateinit var adapter: FetchRewardItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_fetch_reward_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFetchRewardRecyclerView()
    }

    override fun invalidate() {
        withState(fetchRewardListviewModel) { state ->
            renderList(state.fetchRewardListUIState)
        }
    }

    private fun renderList(uiState: FetchRewardListUIState) {
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
            view.layoutManager = LinearLayoutManager(requireContext())
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
        Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_LONG).show()
    }

}