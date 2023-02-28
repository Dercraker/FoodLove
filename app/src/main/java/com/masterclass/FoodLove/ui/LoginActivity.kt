package com.masterclass.FoodLove.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.masterclass.MainActivity
import com.example.masterclass.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var btnCancel: MaterialButton
    private lateinit var btnLogin: MaterialButton
    private lateinit var btnSwitchLoginRegister: MaterialButton
    private var username: TextInputEditText? = null
    private var password: EditText? = null
    private var user:FirebaseUser? = null
    private var isAllFieldsChecked: Boolean = false
    private var authRepository: AuthRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnCancel = findViewById(R.id.login_btn_cancel)
        btnLogin = findViewById(R.id.login_btn_login)
        btnSwitchLoginRegister = findViewById(R.id.login_btn_switch_register_login)

        user = authRepository.getCurrentUser()
        if (user != null) startActivity(Intent(this,MainActivity::class.java))

        username = findViewById(R.id.login_username)
        username?.addTextChangedListener {
            username?.error = null
        }
        password = findViewById(R.id.login_password)
        password?.addTextChangedListener {
            password?.error = null
        }
        btnSwitchLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked) {
                authRepository.loginAccount(username!!.text.toString(), password!!.text.toString()) {
                    user = authRepository.getCurrentUser()
                    if (it == null && user != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        it?.let {
                            if (it is FirebaseAuthInvalidCredentialsException && it.errorCode == "ERROR_INVALID_EMAIL")
                                username?.error = it.message
                            else if (it is FirebaseAuthInvalidCredentialsException && it.errorCode == "ERROR_WRONG_PASSWORD") {
                                username?.error = it.message
                                password?.error = it.message
                            } else if (it is FirebaseAuthInvalidUserException && it.errorCode == "ERROR_USER_NOT_FOUND")
                                username?.error = it.message
                            else  {
                                username?.error = "An unidentified error has occurred, please try again later"
                            }
                        }
                    }
                }
            }
        }

        btnCancel.setOnClickListener {
            username?.text?.clear()
            password?.text?.clear()
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
        return true
    }
}