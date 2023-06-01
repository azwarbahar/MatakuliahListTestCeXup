package com.azwar.studentlist.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dosen_table")
data class Dosen(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dosen_id")
    val id: Int = 0,
    @ColumnInfo(name = "nid")
    val nid: String?,
    @ColumnInfo(name = "dosen_nama")
    val nama: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nid)
        parcel.writeString(nama)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dosen> {
        override fun createFromParcel(parcel: Parcel): Dosen {
            return Dosen(parcel)
        }

        override fun newArray(size: Int): Array<Dosen?> {
            return arrayOfNulls(size)
        }
    }
}