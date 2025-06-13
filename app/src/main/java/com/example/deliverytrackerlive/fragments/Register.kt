package com.example.deliverytrackerlive.fragments

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deliverytrackerlive.R
import com.example.deliverytrackerlive.databinding.FragmentRegisterBinding
import com.example.deliverytrackerlive.viewmodel.MainViewModel

class Register : Fragment() {
    private lateinit var bind: FragmentRegisterBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        Log.d("charu", "onCreateView")

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.tvLoginAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        bind.btnRegister.setOnClickListener {
            if (checkInput()) {
                bind.apply {
                    val checkedId = mtgSelectType.checkedButtonId
                    val type = when (checkedId) {
                        R.id.btn_DeliveryBoy -> "Delivery Boy"
                        R.id.btn_Customer -> "Customer"
                        else -> ""
                    }
                    val email = tfEmail.text.toString()
                    val password = tfPassword.text.toString()
                    Log.d("charu", "$email $password $type")
                    mainViewModel.signUp(email, password, type)
                }
            }
        }
    }

    private fun checkInput(): Boolean {
        return bind.tfEmail.text.toString().isNotEmpty() && bind.tfPassword.text.toString().isNotEmpty()
    }
}