package com.azwar.studentlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.azwar.studentlist.databinding.ActivitySplashScreenBinding
import com.azwar.studentlist.utils.AppPreferences
import com.azwar.studentlist.viewmodel.MainViewModel
import com.azwar.studentlist.viewmodel.MainViewModelFactory

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private lateinit var appPreferences : AppPreferences

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appPreferences = AppPreferences.getInstance(application)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // check
        if (!appPreferences.isDummyDataInserted()) {
            mainViewModel.insertDummyData()
            // Set Preference
            appPreferences.setDummyDataInserted()
        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}