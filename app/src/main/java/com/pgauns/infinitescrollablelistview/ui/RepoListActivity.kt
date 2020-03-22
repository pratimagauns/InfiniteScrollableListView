package com.pgauns.infinitescrollablelistview

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.pgauns.infinitescrollablelistview.ui.adapters.RepoListAdapter
import com.pgauns.infinitescrollablelistview.ui.listeners.InfiniteScrollListener
import com.pgauns.infinitescrollablelistview.ui.viewmodel.RepoListViewModel

class RepoListActivity : AppCompatActivity() {

    lateinit var viewModel: RepoListViewModel
    var repoListAdapter: RepoListAdapter? = null
    lateinit var scrollUpAction: View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)

        val listView =
            findViewById<ListView>(R.id.repo_listview)

        viewModel.getRepoList().observe(this, androidx.lifecycle.Observer {repositories ->
            if (repoListAdapter == null) {
                repoListAdapter = RepoListAdapter(applicationContext, repositories)
                listView.adapter = repoListAdapter
            }
            else {
                repoListAdapter!!.updateDataSource(repositories)
            }
        })

        listView.setOnScrollListener(infiniteScrollListener)

        scrollUpAction = findViewById(R.id.repo_list_scroll_top)
        scrollUpAction.setOnClickListener { view ->
            listView.smoothScrollToPosition(0)
            scrollUpAction.visibility = View.INVISIBLE
        }
    }

    var infiniteScrollListener: InfiniteScrollListener = object : InfiniteScrollListener() {
        override fun onLoadMore(currentPage: Int, page: Int, totalItemsCount: Int): Boolean {
            viewModel.loadMore()
            repoListAdapter!!.notifyDataSetChanged()
            return true
        }
    }
}
