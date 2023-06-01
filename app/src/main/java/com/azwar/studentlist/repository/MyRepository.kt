package com.azwar.studentlist.repository

import com.azwar.studentlist.data.dao.MyDao
import com.azwar.studentlist.data.entities.*

class MyRepository(private val myDao: MyDao) {

    suspend fun getAllMataKuliah(): List<MataKuliah>{
        return myDao.getAllMataKuliah()
    }

    suspend fun insertMataKuliah(mataKuliah: MataKuliah) {
        myDao.insertMataKuliah(mataKuliah)
    }

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        myDao.insertMahasiswa(mahasiswa)
    }

    suspend fun insertDosen(dosen: Dosen) {
        myDao.insertDosen(dosen)
    }

}