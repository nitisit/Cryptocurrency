package com.example.cryptocurrency.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.cryptocurrency.data.model.CoinX
import com.example.cryptocurrency.data.remote.CoinRepository

class CoinViewModel(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private var coins: LiveData<PagedList<CoinX>>
    private var stateRefresh: LiveData<Boolean>

    init {
        coinRepository.loadCoin().let {
            coins = it.coins
            stateRefresh = it.stateRefresh
        }
    }

    fun getCoin(): LiveData<PagedList<CoinX>> = coins

    fun getStateRefresh(): LiveData<Boolean> = stateRefresh

    fun searchSymbol(symbol: String) {
        coinRepository.searchSymbol(symbol)
    }

    fun getRefresh() {
        coinRepository.loadRefresh()
    }
}
