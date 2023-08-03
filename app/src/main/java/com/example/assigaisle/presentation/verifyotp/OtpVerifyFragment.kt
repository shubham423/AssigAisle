package com.example.assigaisle.presentation.verifyotp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assigaisle.databinding.FragmentOtpVerifyBinding

class OtpVerifyFragment : Fragment() {
    private lateinit var binding:FragmentOtpVerifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOtpVerifyBinding.inflate(layoutInflater)
        return binding.root
    }
}