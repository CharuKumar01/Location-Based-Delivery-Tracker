package com.example.deliverytrackerlive.fragments

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.deliverytrackerlive.R
import com.example.deliverytrackerlive.databinding.FragmentLoginBinding

class Login : Fragment() {
    private lateinit var bind: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.tvCreateAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}