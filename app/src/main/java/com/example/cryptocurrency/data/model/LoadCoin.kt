package com.example.cryptocurrency.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class LoadCoin<T>(
    val coins: LiveData<PagedList<T>>,
    val stateRefresh: LiveData<Boolean>
)