package com.admin.chalengesicredi.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.admin.chalengesicredi.R
import com.admin.chalengesicredi.adapter.CellClickListene
import com.admin.chalengesicredi.adapter.EventAdapter
import com.admin.chalengesicredi.base.BaseActivity
import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.Event
import com.admin.chalengesicredi.model.EventResult
import com.admin.chalengesicredi.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity: BaseActivity<MainViewModel>(), CellClickListene {

    private val adapter = EventAdapter(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // divisao de itens recycleview
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        setupScrollListener()
        initAdapter()
        initSearch()


    }

    private fun initSearch() {

        viewModel.searchRepo()

    }



    private fun initAdapter() {
        list.adapter = adapter
        var listrepo: List<Event>

        viewModel.repoResult.observe(this) { result ->
            when (result) {
                is EventResult.Success -> {

                    adapter.submitList(result.data.distinct())
                }
                is EventResult.Error -> {
                    Toast.makeText(
                        this,
                        "\uD83D\uDE28 Falha $result.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupScrollListener() {
        val layoutManager = list.layoutManager as LinearLayoutManager
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }


    override fun onCellClickListener(repo: Event) {


        val intent = Intent(this, ActivityDetailEvent::class.java )
        intent.putExtra("idevent", repo.id)
        startActivity(intent)
    }

    override fun onCellClickListener2(data: DetailEvent) {
        TODO("Not yet implemented")
    }
    override fun getViewModel() = MainViewModel::class.java
    override val layoutRes = R.layout.main_activity
}