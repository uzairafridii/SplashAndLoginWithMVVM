package com.uzair.mvvmbasics.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uzair.mvvmbasics.R
import com.uzair.mvvmbasics.viewmodel.SplashViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /// init view model
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        /// start coroutine
        splashViewModel.splashState()
        /// observer for splash screen state
        val observer = Observer<SplashViewModel.SplashState> {
            goToHomeScreen()
        }

//        observe live data
//        which is actually list we observe it whenever any data is
//        added its observe and change the
          splashViewModel.liveData.observe(this, observer)

    }


    /// goto main screen
    private fun goToHomeScreen() {
        finish()
        startActivity(Intent(this, HomeScreen::class.java))
    }
}