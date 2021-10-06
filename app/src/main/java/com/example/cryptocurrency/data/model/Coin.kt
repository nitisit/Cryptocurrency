package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class Coin(
    val `data`: Data?,
    val status: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Data::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(data, flags)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}