package com.example.cryptocurrency

import com.bumptech.glide.Glide
import com.example.cryptocurrency.network.RetrofitClient
import com.example.cryptocurrency.presentation.viewmodel.CoinViewModel
import com.example.cryptocurrency.data.remote.CoinApiService
import com.example.cryptocurrency.data.remote.CoinRepository
import com.kho.beerpaginglivedata.data.datasource.pagekey.CoinPageKeyDataFactory
import com.kho.beerpaginglivedata.data.datasource.pagekey.CoinPageKeyDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoinViewModel(get()) }
}

val repositoryModule = module {
    single { CoinRepository(get()) }
}

val apiServiceModule = module {
    single { RetrofitClient.provideRetrofit(BuildConfig.BASE_URL) }
    single { RetrofitClient.provideApiService(get(), CoinApiService::class.java) }
}

val utilModule = module {
    single { Glide.with(androidContext()) }
    single { (symbol: String) -> CoinPageKeyDataSource(get(), symbol) }
    single { CoinPageKeyDataFactory(get()) }
    single { imageLoader(androidContext()) }
}
