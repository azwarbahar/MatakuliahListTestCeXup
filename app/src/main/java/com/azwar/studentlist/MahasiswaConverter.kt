package com.azwar.studentlist

import androidx.room.TypeConverter
import com.azwar.studentlist.data.entities.Mahasiswa
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MahasiswaConverter {
    @TypeConverter
    fun fromMahasiswaList(mahasiswaList: List<Mahasiswa>): String {
        val gson = Gson()
        return gson.toJson(mahasiswaList)
    }

    @TypeConverter
    fun toMahasiswaList(mahasiswaString: String): List<Mahasiswa> {
        val listType = object : TypeToken<List<Mahasiswa>>() {}.type
        val gson = Gson()
        return gson.fromJson(mahasiswaString, listType)
    }
}
