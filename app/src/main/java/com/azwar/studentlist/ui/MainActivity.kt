package com.azwar.studentlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.azwar.studentlist.R
import com.azwar.studentlist.adapter.MatakuliahAdapter
import com.azwar.studentlist.databinding.ActivityMainBinding
import com.azwar.studentlist.utils.AppPreferences
import com.azwar.studentlist.viewmodel.MainViewModel
import com.azwar.studentlist.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var matakuliahAdapter: MatakuliahAdapter

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private lateinit var swipe_refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)

        mainViewModel.getAllMataKuliah()

        swipe_refresh = binding.swipeRefresh
        swipe_refresh.setOnRefreshListener(this)
        swipe_refresh.setColorSchemeResources(
            R.color.purple_500,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_green_dark
        )
        swipe_refresh.post(Runnable {
            loadData()
        })

        binding.rvMataKuliah.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

//                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
//                    mainViewModel.loadMoreMahasiswaData()
//                }
            }
        })

    }

    private fun loadData() {
        mainViewModel.mataKuliah.observe(this, Observer {
            binding.rvMataKuliah.layoutManager = LinearLayoutManager(this)
            matakuliahAdapter = MatakuliahAdapter(it)
            binding.rvMataKuliah.adapter = matakuliahAdapter
            swipe_refresh.isRefreshing = false
        })
    }

    override fun onRefresh() {
        loadData()
    }
}