package com.example.deliverytrackerlive.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliverytrackerlive.firebase.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<FirebaseUser?>()
    val authState: MutableLiveData<FirebaseUser?> = _authState

    private val _userType = MutableLiveData<String?>()
    val userType: MutableLiveData<String?> = _userType

    private val _userAlreadyExists = MutableLiveData<Boolean>()
    val userAlreadyExists: MutableLiveData<Boolean> = _userAlreadyExists


    fun signUp(email: String, password: String, type: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = auth.currentUser?.uid ?: return@addOnCompleteListener
                val user = User(uid, email, type)
                FirebaseFirestore.getInstance().collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener { _authState.value = auth.currentUser }
            } else {
                val error = task.exception
                Log.e("charu", "Firebase Auth failed: ${error?.message}", error)
                _userAlreadyExists.value = error is FirebaseAuthUserCollisionException
                _authState.value = null
            }
        }
    }

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _authState.value = auth.currentUser
                Log.d("charu", "Login Successful, uid = ${auth.currentUser?.uid}")
            } else {
                Log.e("charu", "login failed, ${task.exception?.message}", task.exception)
            }
        }
    }

    fun checkAuth(){
        _authState.value = auth.currentUser
    }

    fun fetchUserType() {
        val uid = auth.currentUser?.uid ?: return
        FirebaseFirestore.getInstance().collection("users").document(uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val type = it.getString("type")
                    _userType.value = type
                    Log.d("charu", "Fetched user type: $type")
                }
            }
    }


}