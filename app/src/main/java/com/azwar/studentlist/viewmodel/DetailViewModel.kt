package com.azwar.studentlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azwar.studentlist.data.entities.Mahasiswa

class DetailViewModel() : ViewModel() {

    lateinit var mahasiswas: List<Mahasiswa>

    private var filterQuery: String = ""

    fun setDataList(dataList: List<Mahasiswa>) {
        this.mahasiswas = dataList
    }

    fun getMahasiswaList(): List<Mahasiswa> {
        return mahasiswas
    }

    fun filterMahasiswa(query: String) {
        filterQuery = query
    }

    fun getFilteredMahasiswaList(): List<Mahasiswa> {
        return if (filterQuery.isEmpty()) {
            mahasiswas
        } else {
            mahasiswas.filter { mahasiswa ->
                mahasiswa.nama!!.contains(filterQuery, ignoreCase = true) ||
                        mahasiswa.nim!!.contains(filterQuery, ignoreCase = true)
            }
        }
    }

}