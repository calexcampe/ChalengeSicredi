package com.admin.chalengesicredi.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.admin.chalengesicredi.R
import com.admin.chalengesicredi.adapter.DetailAdapter
import com.admin.chalengesicredi.adapter.PeopleAdapter
import com.admin.chalengesicredi.base.BaseActivity
import com.admin.chalengesicredi.model.DetailEventResult
import com.admin.chalengesicredi.ui.main.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail_event_activity.*

class ActivityDetailEvent : BaseActivity<DetailViewModel>() {

    private val adapter = DetailAdapter()
    private lateinit var peopleAdapter : PeopleAdapter
    lateinit var bundle: Bundle
    lateinit var idevent: String

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)


        bundle = intent.extras!!
        idevent = bundle.get("idevent").toString()

        setupScrollListener()
        configtoolba()
        initAdapter()
        initSearch()
        listener()
    }

    fun listener(){

        btncheckin.setOnClickListener {

            val intent = Intent(this, CheckinActivity::class.java)
            intent.putExtra("id", idevent)
            startActivity(intent)
        }
    }
    fun configtoolba(){

        toolbar.setNavigationIcon(R.drawable.backwhite)
        toolbar.setNavigationOnClickListener {

            onBackPressed()
        }
    }

    private fun initSearch() {

        viewModel.searchRepo(idevent)

    }

    private fun initAdapter() {

        peopleAdapter = PeopleAdapter()
        listdetail.adapter = adapter
        listpeople.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        listpeople.adapter = peopleAdapter
        viewModel.repoResult.observe(this) { result ->
            when (result) {
                is DetailEventResult.Success -> {
                    adapter.submitList(listOf(result.data))
                    peopleAdapter.submitList(result.datap)
                }
                is DetailEventResult.Error -> {
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
        val layoutManager = listpeople.layoutManager as LinearLayoutManager
        listpeople.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

    override fun getViewModel() = DetailViewModel::class.java
    override val layoutRes = R.layout.activity_detail_event_activity
}