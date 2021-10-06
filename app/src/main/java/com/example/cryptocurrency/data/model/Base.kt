package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class Base(
    val sign: String?,
    val symbol: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sign)
        parcel.writeString(symbol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Base> {
        override fun createFromParcel(parcel: Parcel): Base {
            return Base(parcel)
        }

        override fun newArray(size: Int): Array<Base?> {
            return arrayOfNulls(size)
        }
    }
}