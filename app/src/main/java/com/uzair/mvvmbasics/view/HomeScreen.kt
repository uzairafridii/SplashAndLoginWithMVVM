package com.uzair.mvvmbasics.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import com.uzair.mvvmbasics.R
import com.uzair.mvvmbasics.viewmodel.LoginViewModel

class HomeScreen : AppCompatActivity() {

    /// views

    @BindView(R.id.btnContinue)
    lateinit var btnContinue: Button
    @BindView(R.id.licenseId)
    lateinit var licenseId: EditText
    /// view model
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        ButterKnife.bind(this)

        initViews()

        /// button click listener
        btnContinue.setOnClickListener {
            /// get user result
            if (TextUtils.isEmpty(licenseId.text.toString().trim())) {
                Toast.makeText(
                    applicationContext,
                    "License id fields can't be empty",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // login method called
                loginViewModel.login(licenseId.text.toString().trim())
                observer() // observer to observe the status
            }


        }


    }

    private fun observer() {
        /// observer
        val observer = Observer<Result<String>> { result ->
            result.onSuccess {
                if (result.getOrNull().equals("Success", ignoreCase = true)) {
                    startActivity(Intent(this, Dashboard::class.java))
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                    loginViewModel.resetStatus()
                }
            }
            result.onFailure {
                Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
                loginViewModel.resetStatus()
            }

        }

        /// observe list
        loginViewModel.getUserStatus().observe(this, observer)
    }

    private fun initViews() {
        // init view model
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }
}