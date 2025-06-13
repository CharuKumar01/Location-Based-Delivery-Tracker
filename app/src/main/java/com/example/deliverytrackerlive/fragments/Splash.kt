package com.example.deliverytrackerlive.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.deliverytrackerlive.R
import com.example.deliverytrackerlive.databinding.FragmentSplashBinding
import com.example.deliverytrackerlive.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash : Fragment() {
    private lateinit var bind: FragmentSplashBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        navigation()

        return bind.root
    }

    private fun navigation() {
        mainViewModel.authState.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().navigate(R.id.action_splash_to_register)
            }else {
                findNavController().navigate(R.id.action_splash_to_home2)
            }
        }

//        lifecycleScope.launch {
//            delay(300)
            mainViewModel.checkAuth()
//        }
    }
}