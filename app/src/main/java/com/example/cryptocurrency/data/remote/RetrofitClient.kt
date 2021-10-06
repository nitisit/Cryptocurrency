package com.example.cryptocurrency.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor {
                try {
                    it.proceed(it.request())
                } catch (exception: Exception) {
                    when (exception) {
                        is SocketTimeoutException, is ConnectException ->
                            Response.Builder()
                                .request(it.request())
                                .protocol(Protocol.HTTP_1_0)
                                .message("Timeout Error")
                                .body(
                                    ResponseBody.create(
                                        "application/text; charset=utf-8".toMediaTypeOrNull(),
                                        "Timeout Error"
                                    )
                                )
                                .code(408)
                                .build()
                        else -> throw exception
                    }
                }
            }
            .build()
    }

    fun provideRetrofit(
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> provideApiService(retrofit: Retrofit, apiServiceClass: Class<T>): T {
        return retrofit.create(apiServiceClass)
    }
}
