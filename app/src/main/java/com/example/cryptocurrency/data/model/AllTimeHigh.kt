package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class AllTimeHigh(
    val price: String?,
    val timestamp: Long?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(price)
        parcel.writeValue(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllTimeHigh> {
        override fun createFromParcel(parcel: Parcel): AllTimeHigh {
            return AllTimeHigh(parcel)
        }

        override fun newArray(size: Int): Array<AllTimeHigh?> {
            return arrayOfNulls(size)
        }
    }
}