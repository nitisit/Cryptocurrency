package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class Social(
    val name: String?,
    val type: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Social> {
        override fun createFromParcel(parcel: Parcel): Social {
            return Social(parcel)
        }

        override fun newArray(size: Int): Array<Social?> {
            return arrayOfNulls(size)
        }
    }
}