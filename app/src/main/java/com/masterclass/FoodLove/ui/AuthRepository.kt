package com.masterclass.FoodLove.ui

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {

    private var auth: FirebaseAuth = Firebase.auth

    fun createAccount(mail: String, password: String, completion: (exception: Exception?) -> Unit) {
        auth.createUserWithEmailAndPassword(mail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    completion(null)
                else
                    completion(task.exception)
            }
    }

    fun loginAccount(username: String, password: String, completion: (exception: Exception?) -> Unit) {
        auth.signInWithEmailAndPassword(username,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    completion(null)
                else
                    completion(task.exception)
            }
    }

    fun logoutAccount() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }

}