package com.uzair.mvvmbasics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {

    val liveData: LiveData<SplashState> get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    fun splashState() {
        /// coroutine to delay for 1 sec and move to next screen
        GlobalScope.launch {
            delay(1000)
            mutableLiveData.postValue(SplashState.HomeActivity())
        }

    }


    /// create splash state class
    sealed class SplashState {
        class HomeActivity : SplashState()
    }

}