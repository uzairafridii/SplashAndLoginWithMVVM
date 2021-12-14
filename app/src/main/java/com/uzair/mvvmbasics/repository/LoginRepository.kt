package com.uzair.mvvmbasics.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class LoginRepository {

    private val _signInStatus = MutableLiveData<Result<String>>()
    var signInStatus: LiveData<Result<String>> = _signInStatus
    // init with lazy keyword
    private val dbRef: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("LicenseId")
    }

    /// reset status
    fun resetSignInLiveData() {
        _signInStatus.value = Result.success("Reset")
    }

    // login status
    fun getLoginStatus(): LiveData<Result<String>> {
        return signInStatus
    }

    // login with id
    fun login(companyId: String) {
        // check for company id in firebase
        // if exist then go to home screen
        dbRef.child(companyId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        _signInStatus.postValue(Result.success("Success"))
                    } else {
                        _signInStatus.postValue(Result.failure(object : Throwable() {
                        }))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }
}