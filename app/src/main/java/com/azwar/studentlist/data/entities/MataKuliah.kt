package com.azwar.studentlist.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.azwar.studentlist.MahasiswaConverter

@Entity(tableName = "matakuliah_table")
data class MataKuliah(
    @PrimaryKey
    @ColumnInfo(name = "mata_kuliah_id")
    val id: Int?,
    @ColumnInfo(name = "mata_kuliah_nama")
    val nama: String?,
    @Embedded
    val dosen: Dosen?,
    @TypeConverters(MahasiswaConverter::class)
    val mahasiswa: List<Mahasiswa>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readParcelable(Dosen::class.java.classLoader),
        parcel.createTypedArrayList(Mahasiswa)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nama)
        parcel.writeParcelable(dosen, flags)
        parcel.writeTypedList(mahasiswa)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MataKuliah> {
        override fun createFromParcel(parcel: Parcel): MataKuliah {
            return MataKuliah(parcel)
        }

        override fun newArray(size: Int): Array<MataKuliah?> {
            return arrayOfNulls(size)
        }
    }
}

