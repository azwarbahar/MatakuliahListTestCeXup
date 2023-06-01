package com.azwar.studentlist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.azwar.studentlist.MahasiswaConverter
import com.azwar.studentlist.data.dao.MyDao
import com.azwar.studentlist.data.entities.*

@Database(entities = [Dosen::class, Mahasiswa::class, MataKuliah::class], version = 1)
@TypeConverters(MahasiswaConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}