package com.example.deliverytrackerlive.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.deliverytrackerlive.R
import com.example.deliverytrackerlive.databinding.FragmentHomeBinding
import com.example.deliverytrackerlive.viewmodel.MainViewModel


class Home : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        Log.d("charu", "onCreateView")
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.userType.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("charu", "User Type: $it")
            }
        }

        mainViewModel.fetchUserType()
    }

}