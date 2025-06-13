package com.example.deliverytrackerlive.fragments

import android.graphics.Paint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.deliverytrackerlive.R
import com.example.deliverytrackerlive.databinding.FragmentLoginBinding
import com.example.deliverytrackerlive.viewmodel.MainViewModel
import java.util.function.LongFunction

class Login : Fragment() {
    private lateinit var bind: FragmentLoginBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.tvCreateAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        bind.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        bind.btnLogin.setOnClickListener {
            signIn()
        }

        mainViewModel.authState.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_login_to_home2)
                Log.d("charu", "${it.uid}")
            }
        }
    }

    private fun signIn() {
        if (checkInput()) {
            bind.apply {
                val email = tfEmail.text.toString()
                val password = tfPassword.text.toString()
                mainViewModel.signIn(email, password)
            }
        }
    }

    private fun checkInput(): Boolean {
        return bind.tfEmail.text.toString().isNotEmpty() && bind.tfPassword.text.toString().isNotEmpty()
    }
}