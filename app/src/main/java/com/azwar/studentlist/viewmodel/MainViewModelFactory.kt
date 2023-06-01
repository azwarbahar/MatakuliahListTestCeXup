package com.azwar.studentlist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azwar.studentlist.data.database.MyDatabase
import com.azwar.studentlist.repository.MyRepository

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val myDao = MyDatabase.getInstance(application).myDao()
            val repository = MyRepository(myDao)
            return MainViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}