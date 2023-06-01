package com.azwar.studentlist

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.azwar.studentlist.data.database.MyDatabase

class MyApp : Application() {
    companion object {
        lateinit var database: MyDatabase
    }

    override fun onCreate() {
        super.onCreate()

        synchronized(this) {
            Room.databaseBuilder(
                applicationContext,
                MyDatabase::class.java,
                "my_database"
            ).build()
        }

//        database = Room.databaseBuilder(
//            applicationContext,
//            MyDatabase::class.java,
//            "my_database"
//        ).build()
    }
}