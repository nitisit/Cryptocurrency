package com.kho.beerpaginglivedata.data.datasource.pagekey

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.cryptocurrency.data.model.Coin
import com.example.cryptocurrency.data.model.CoinX
import com.example.cryptocurrency.data.remote.CoinApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinPageKeyDataSource(
    private val coinApiService: CoinApiService,
    private val symbol: String
) : PageKeyedDataSource<Int, CoinX>() {

    val initialState = MutableLiveData<Boolean>()
    private var offset = 0
    private var limit = 10

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CoinX>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            initialState.postValue(true)
            var response =
                if (symbol.isNotEmpty()) getSearchCoin(symbol) else getCoin(offset, limit)
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                initialState.postValue(false)
                callback.onResult(getCoinInfo(responseBody), 10, 10)
            } else {
                initialState.postValue(false)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CoinX>) {
        CoroutineScope(Dispatchers.IO).launch {
            if (symbol.isEmpty()) {
                offset += limit
                val response = getCoin(offset, limit)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    callback.onResult(getCoinInfo(responseBody), 10)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CoinX>) {
    }

    private suspend fun getCoin(offset: Int, limit: Int): Response<Coin> {
        return coinApiService.getCoin(offset, limit)
    }

    private suspend fun getSearchCoin(search: String): Response<Coin> {
        return coinApiService.searchCoin(search)
    }

    private fun getCoinInfo(coin: Coin): ArrayList<CoinX> {
        var coinX = ArrayList<CoinX>()
        coin.data?.coins?.map {
            coinX.add(it)
        }
        return coinX
    }
}