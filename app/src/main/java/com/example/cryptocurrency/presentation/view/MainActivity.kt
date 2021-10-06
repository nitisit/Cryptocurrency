package com.example.cryptocurrency.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.example.cryptocurrency.*
import com.example.cryptocurrency.data.model.CoinX
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.presentation.viewmodel.CoinViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    private val coinViewModel: CoinViewModel by inject()
    private lateinit var binding: ActivityMainBinding
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var coin: PagedList<CoinX>
    private val imageLoader: ImageLoader by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        startKoin {
            androidContext(this@MainActivity)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    apiServiceModule,
                    utilModule
                )
            )
        }

        supportActionBar?.hide()
        coinViewModel.getCoin().observe(this, Observer {
            coin = it
            coinAdapter.submitList(it)
        })

        coinViewModel.getStateRefresh().observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = it
        })

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                coinViewModel.searchSymbol(s.toString())
                coinViewModel.getRefresh()
            }
        })

        createAdapter()
        handlePullToRefresh()
    }

    private fun createAdapter() {
        coinAdapter = CoinAdapter(imageLoader)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = coinAdapter
    }

    private fun handlePullToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            coinViewModel.getRefresh()
        }
    }
}