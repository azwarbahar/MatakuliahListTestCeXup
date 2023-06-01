package com.azwar.studentlist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.azwar.studentlist.data.entities.*

@Dao
interface MyDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    @Insert
    suspend fun insertMataKuliah(mataKuliah: MataKuliah)

    @Transaction
    @Query("SELECT * FROM mataKuliah_table")
    suspend fun getAllMataKuliah(): List<MataKuliah>
}