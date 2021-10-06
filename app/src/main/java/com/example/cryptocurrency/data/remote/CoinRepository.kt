package com.example.cryptocurrency.data.remote

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cryptocurrency.data.model.CoinX
import com.example.cryptocurrency.data.model.LoadCoin
import com.kho.beerpaginglivedata.data.datasource.pagekey.CoinPageKeyDataFactory
import com.kho.beerpaginglivedata.data.datasource.pagekey.CoinPageKeyDataSource

class CoinRepository(
    private val dataSourceFactory: CoinPageKeyDataFactory
) {

    private val pageSize = 10
    fun loadCoin(): LoadCoin<CoinX> {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(false)
            .build()
        return initializedPagedListBuilder(config)
    }

    fun loadRefresh() {
        dataSourceFactory.dataSource.value?.invalidate()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LoadCoin<CoinX> {
        val stateRefresh = Transformations.switchMap<CoinPageKeyDataSource, Boolean>(
            dataSourceFactory.dataSource, { it.initialState })
        val coins = LivePagedListBuilder<Int, CoinX>(dataSourceFactory, config).build()
        return LoadCoin(coins, stateRefresh)
    }

    fun searchSymbol(symbol: String) {
        dataSourceFactory.symbol = symbol
        dataSourceFactory.dataSource.value?.invalidate()
    }
}