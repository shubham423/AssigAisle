package com.example.assigaisle.presentation.verifyotp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.assigaisle.data.models.VerifyOtpRequest
import com.example.assigaisle.databinding.FragmentOtpVerifyBinding
import com.example.assigaisle.presentation.login.LoginFragment.Companion.MOBILE_NUMBER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerifyFragment : Fragment() {
    private lateinit var binding:FragmentOtpVerifyBinding
    private val viewModel: VerifyOtpViewModel by viewModels()
    private var number=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOtpVerifyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number= arguments?.getString(MOBILE_NUMBER).toString()
        binding.tvMobileNumber.text=number
        initClickListeners()
        initObservers()
    }

    private fun initObservers() {

    }

    private fun initClickListeners() {
        binding.btnContinue.setOnClickListener {
            val otp=binding.etOtp.text
            if (otp.isNotEmpty()){
                viewModel.verifyOtp(VerifyOtpRequest(number.trim(),otp.toString()))
            }else{
                Toast.makeText(requireContext(), "please enter a valid otp", Toast.LENGTH_SHORT).show()
            }

        }
    }
}