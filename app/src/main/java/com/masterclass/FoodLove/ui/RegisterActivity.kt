package com.masterclass.FoodLove.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.masterclass.MainActivity
import com.example.masterclass.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser


class RegisterActivity : AppCompatActivity() {

    //private lateinit var analytics : FirebaseAnalytics
    private var username: EditText? = null
    private var password: EditText? = null
    private var cPassword: EditText? = null
    private lateinit var btnRegister: MaterialButton
    private lateinit var btnSwitchRegisterLogin: MaterialButton
    private lateinit var authRepository: AuthRepository
    private var user:FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //analytics = FirebaseAnalytics.getInstance(this)
        authRepository = AuthRepository()
        username = findViewById(R.id.register_username)
        password = findViewById(R.id.register_password)
        cPassword = findViewById(R.id.register_cpassword)
        btnRegister = findViewById(R.id.register_btn_register)
        btnSwitchRegisterLogin = findViewById(R.id.register_btn_switch_register_login)
        btnRegister.setOnClickListener {
            //  TODO: Check password an conf password
            //  TODO: Check values validity
            if (checkAllFields()){
                authRepository.createAccount(username?.text.toString(), password?.text.toString()) {
                    user = authRepository.getCurrentUser()
                    if (it == null && user != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        if (it is FirebaseAuthInvalidCredentialsException && it.errorCode == "ERROR_INVALID_EMAIL")
                            username?.error = it.message
                        else if (it is FirebaseAuthWeakPasswordException && it.errorCode == "ERROR_WEAK_PASSWORD")
                            password?.error = it.message
                        else if (it is FirebaseAuthUserCollisionException && it.errorCode == "ERROR_EMAIL_ALREADY_IN_USE")
                            username?.error = it.message
                        else  {
                            username?.error = "An unidentified error has occurred, please try again later"
                        }
                    }
                }
            }
        }

        btnSwitchRegisterLogin.setOnClickListener {
            val loginActivityIntent: Intent = Intent(this, LoginActivity::class.java )
            startActivity(loginActivityIntent)
        }
    }
    private fun checkAllFields(): Boolean {
        if (username?.text?.toString()?.length == 0){
            username?.error = "Le champ \"Username\" est requis"
            return false
        }
        if (password?.text?.toString()?.length == 0) {
            password?.error = "Le champ \"Password\" est requis"
            return false
        }
        if (cPassword?.text?.toString()?.length == 0){
            cPassword?.error = "Le champ \"Confirm Password\" est requis"
            return false
        }
        if (cPassword?.text?.toString() != password?.text?.toString()){
            cPassword?.error = "Le champ \"Confirm Password\" est différent du champ \"Password\""
            return false
        }
        return true
    }
}