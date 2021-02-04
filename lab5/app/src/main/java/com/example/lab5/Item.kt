package com.example.lab5

import android.os.Parcel
import android.os.Parcelable

class Item(): Parcelable {
    var kind: String = ""
    var title: String = ""
    var price: Double = 0.0
    var weight: Double = 0.0
    var manufacturer: String = ""

    val info: String
        get() = "$kind $title $price₽ $weight $manufacturer"

    constructor(parcel: Parcel) : this() {
        kind = parcel.readString() ?: ""
        title = parcel.readString() ?: ""
        price = parcel.readDouble()
        weight = parcel.readDouble()
        manufacturer = parcel.readString() ?: ""
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (dest !== null) {
            dest.writeString(kind)
            dest.writeString(title)
            dest.writeDouble(price)
            dest.writeDouble(weight)
            dest.writeString(manufacturer)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
