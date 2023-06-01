package com.azwar.studentlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azwar.studentlist.data.entities.Dosen
import com.azwar.studentlist.data.entities.Mahasiswa
import com.azwar.studentlist.data.entities.MataKuliah
import com.azwar.studentlist.repository.MyRepository
import com.azwar.studentlist.utils.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val myRepository: MyRepository, application: Application) :
    AndroidViewModel(application) {

    //    constructor() : this(getApplication())

    private val _mataKuliahList = MutableLiveData<List<MataKuliah>>()
    val mataKuliah: LiveData<List<MataKuliah>>
        get() = _mataKuliahList

    private var currentPage = 0
    private val pageSize = 10

    fun getAllMataKuliah() {
        viewModelScope.launch {
            val allMataKuliah = myRepository.getAllMataKuliah()
            _mataKuliahList.postValue(allMataKuliah)
        }
    }

    fun insertDummyData() {
        viewModelScope.launch(Dispatchers.IO) {
            // Menambahkan 4 data Dosen
            val dosen1 = Dosen(nid = "1234", nama = "John Doe")
            val dosen2 = Dosen(nid = "5678", nama = "Jane Smith")
            val dosen3 = Dosen(nid = "9101", nama = "Michael Johnson")
            val dosen4 = Dosen(nid = "1121", nama = "Emily Williams")

            myRepository.insertDosen(dosen1)
            myRepository.insertDosen(dosen2)
            myRepository.insertDosen(dosen3)
            myRepository.insertDosen(dosen4)

            // Menambahkan 20 data Mahasiswa
            val mahasiswaList = mutableListOf<Mahasiswa>()
            for (i in 1..20) {
                val mahasiswa = Mahasiswa(nim = "609001160$i", nama = "Mahasiswa $i")
                mahasiswaList.add(mahasiswa)
                myRepository.insertMahasiswa(mahasiswa)
            }

            // Menambahkan 4 data MataKuliah dengan masing-masing 5 Mahasiswa
            for (i in 1..4) {
                val dosen = when (i) {
                    1 -> dosen1
                    2 -> dosen2
                    3 -> dosen3
                    else -> dosen4
                }

                val mahasiswaSubset = mahasiswaList.subList((i - 1) * 5, i * 5)
                val mataKuliah = MataKuliah(i, "Mata Kuliah $i", dosen, mahasiswaSubset)
                myRepository.insertMataKuliah(mataKuliah)
            }
        }
        getAllMataKuliah()
    }

    fun setUpDUmmy() {

    }

}