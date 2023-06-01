package com.azwar.studentlist.utils

import android.content.Context
import android.content.SharedPreferences

class AppPreferences  private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isDummyDataInserted(): Boolean {
        return sharedPreferences.getBoolean(KEY_DUMMY_DATA_INSERTED, false)
    }

    fun setDummyDataInserted() {
        sharedPreferences.edit().putBoolean(KEY_DUMMY_DATA_INSERTED, true).apply()
    }

    companion object {
        private const val PREFS_NAME = "MyAppPrefs"
        private const val KEY_DUMMY_DATA_INSERTED = "dummyDataInserted"

        @Volatile
        private var instance: AppPreferences? = null

        fun getInstance(context: Context): AppPreferences {
            return instance ?: synchronized(this) {
                instance ?: AppPreferences(context).also { instance = it }
            }
        }
    }
}