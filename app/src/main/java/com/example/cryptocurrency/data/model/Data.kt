package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class Data(
    val base: Base?,
    val coins: List<CoinX>?,
    val stats: Stats?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("base"),
        TODO("coins"),
        TODO("stats")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}