package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class Stats(
    val base: String?,
    val limit: Int?,
    val offset: Int?,
    val order: String?,
    val total: Int?,
    val total24hVolume: Double?,
    val totalExchanges: Int?,
    val totalMarketCap: Double?,
    val totalMarkets: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(base)
        parcel.writeValue(limit)
        parcel.writeValue(offset)
        parcel.writeString(order)
        parcel.writeValue(total)
        parcel.writeValue(total24hVolume)
        parcel.writeValue(totalExchanges)
        parcel.writeValue(totalMarketCap)
        parcel.writeValue(totalMarkets)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stats> {
        override fun createFromParcel(parcel: Parcel): Stats {
            return Stats(parcel)
        }

        override fun newArray(size: Int): Array<Stats?> {
            return arrayOfNulls(size)
        }
    }
}