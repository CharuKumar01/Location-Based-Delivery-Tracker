package com.example.deliverytrackerlive.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.deliverytrackerlive.R
import com.example.deliverytrackerlive.databinding.FragmentSplashBinding

class Splash : Fragment() {
    private lateinit var bind: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        findNavController().navigate(R.id.action_splash_to_register)
        return bind.root
    }
}