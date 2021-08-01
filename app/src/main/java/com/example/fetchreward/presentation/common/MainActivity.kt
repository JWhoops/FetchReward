package com.example.fetchreward.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchreward.R
import com.example.fetchreward.presentation.fetchrewardlist.FetchRewardListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchRewardListFragment = FetchRewardListFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragment_container, fetchRewardListFragment)
        transaction.commit()
    }
}