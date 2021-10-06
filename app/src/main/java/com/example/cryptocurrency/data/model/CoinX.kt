package com.example.cryptocurrency.data.model

import android.os.Parcel
import android.os.Parcelable

data class CoinX(
    val allTimeHigh: AllTimeHigh?,
    val approvedSupply: Boolean?,
    val change: Double?,
    val circulatingSupply: Double?,
    val color: String?,
    val confirmedSupply: Boolean?,
    var description: String?,
    val firstSeen: Long?,
    val history: List<String>?,
    val iconType: String?,
    val iconUrl: String?,
    val id: Int?,
    val links: List<Link>?,
    val listedAt: Int?,
    val marketCap: Long?,
    val name: String?,
    val numberOfExchanges: Int?,
    val numberOfMarkets: Int?,
    val penalty: Boolean?,
    val price: String?,
    val rank: Int?,
    val slug: String?,
    val socials: List<Social>?,
    val symbol: String?,
    val totalSupply: Double?,
    val type: String?,
    val uuid: String?,
    val volume: Long?,
    val websiteUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("allTimeHigh"),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("links"),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        TODO("socials"),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(approvedSupply)
        parcel.writeValue(change)
        parcel.writeValue(circulatingSupply)
        parcel.writeString(color)
        parcel.writeValue(confirmedSupply)
        parcel.writeString(description)
        parcel.writeValue(firstSeen)
        parcel.writeStringList(history)
        parcel.writeString(iconType)
        parcel.writeString(iconUrl)
        parcel.writeValue(id)
        parcel.writeValue(listedAt)
        parcel.writeValue(marketCap)
        parcel.writeString(name)
        parcel.writeValue(numberOfExchanges)
        parcel.writeValue(numberOfMarkets)
        parcel.writeValue(penalty)
        parcel.writeString(price)
        parcel.writeValue(rank)
        parcel.writeString(slug)
        parcel.writeString(symbol)
        parcel.writeValue(totalSupply)
        parcel.writeString(type)
        parcel.writeString(uuid)
        parcel.writeValue(volume)
        parcel.writeString(websiteUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinX> {
        override fun createFromParcel(parcel: Parcel): CoinX {
            return CoinX(parcel)
        }

        override fun newArray(size: Int): Array<CoinX?> {
            return arrayOfNulls(size)
        }
    }
}