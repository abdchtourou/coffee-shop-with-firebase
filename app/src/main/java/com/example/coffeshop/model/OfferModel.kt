package com.example.coffeshop.model

import android.os.Parcel
import android.os.Parcelable

data class OfferModel(
    var title: String="",
    var price: Int=0
    , var picUrl: ArrayList<String> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.createStringArrayList() as ArrayList<String>,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(price)
        parcel.writeStringList(picUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OfferModel> {
        override fun createFromParcel(parcel: Parcel): OfferModel {
            return OfferModel(parcel)
        }

        override fun newArray(size: Int): Array<OfferModel?> {
            return arrayOfNulls(size)
        }
    }
}
