package com.uzair.mvvmbasics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.uzair.mvvmbasics.repository.LoginRepository

class LoginViewModel : ViewModel() {

    // using lazy we create an instance of login repository.
    // its object is created when its need or called.
    private val loginRepository: LoginRepository by lazy {
        LoginRepository()
    }

    // get user status
    fun getUserStatus(): LiveData<Result<String>> {
        return loginRepository.getLoginStatus()
    }

    // login with license id
    fun login(licenseId: String) {
        loginRepository.login(licenseId)
    }

    // reset login status
    fun resetStatus() {
        loginRepository.resetSignInLiveData()
    }


}