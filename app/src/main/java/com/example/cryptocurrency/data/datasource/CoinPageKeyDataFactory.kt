package com.kho.beerpaginglivedata.data.datasource.pagekey

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.cryptocurrency.data.model.CoinX
import com.example.cryptocurrency.data.remote.CoinApiService

class CoinPageKeyDataFactory(
    private val coinApiService: CoinApiService
) : DataSource.Factory<Int, CoinX>() {
    val dataSource = MutableLiveData<CoinPageKeyDataSource>()
    var symbol = ""

    override fun create(): DataSource<Int, CoinX> {
        val coinDataSource = CoinPageKeyDataSource(coinApiService, symbol)
        dataSource.postValue(coinDataSource)
        return coinDataSource
    }
}