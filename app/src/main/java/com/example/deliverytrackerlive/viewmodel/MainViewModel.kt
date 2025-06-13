package com.example.deliverytrackerlive.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliverytrackerlive.firebase.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<FirebaseUser?>()
    val authState: MutableLiveData<FirebaseUser?> = _authState

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
                _authState.value = null
            }
        }
    }

}