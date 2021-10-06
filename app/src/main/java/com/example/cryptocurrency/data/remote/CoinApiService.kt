package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.model.Coin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService {
    @GET("/v2/coins")
    suspend fun getCoin(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<Coin>

    @GET("/v2/coins")
    suspend fun searchCoin(
        @Query("symbols") symbol: String
    ): Response<Coin>
}