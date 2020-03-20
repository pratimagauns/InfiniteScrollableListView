package com.pgauns.infinitescrollablelistview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pgauns.infinitescrollablelistview.ui.ViewModel.RepoListViewModel


class RepoListActivity : AppCompatActivity() {

    lateinit var viewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
        viewModel.refresh()
    }
}
