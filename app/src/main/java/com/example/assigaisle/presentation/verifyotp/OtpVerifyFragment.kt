package com.example.assigaisle.presentation.verifyotp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.assigaisle.R
import com.example.assigaisle.data.models.VerifyOtpRequest
import com.example.assigaisle.databinding.FragmentOtpVerifyBinding
import com.example.assigaisle.presentation.login.LoginFragment.Companion.COUNTRY_CODE
import com.example.assigaisle.presentation.login.LoginFragment.Companion.MOBILE_NUMBER
import com.example.assigaisle.utils.Resource
import com.example.assigaisle.utils.gone
import com.example.assigaisle.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OtpVerifyFragment : Fragment() {
    private lateinit var binding: FragmentOtpVerifyBinding
    private val viewModel: VerifyOtpViewModel by viewModels()
    private var number = ""
    private var countryCode = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpVerifyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number = arguments?.getString(MOBILE_NUMBER).toString()
        countryCode = arguments?.getString(COUNTRY_CODE).toString()
        binding.tvMobileNumber.text = number
        initClickListeners()
        initObservers()
        initTimer()
    }

    private fun initTimer() {
        val countdownTime = 60 * 1000 // 60 seconds for OTP validation
        val countDownTimer = object : CountDownTimer(countdownTime.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                millisUntilFinished
                            )
                        )
                binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.tvTimer.text = getString(R.string.text_retry)
            }
        }
        countDownTimer.start()
    }


    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.verifyOtpState.collect {
                when (it) {
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.text_something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visible()
                    }

                    is Resource.Success -> {
                        binding.progressBar.gone()
                        findNavController().navigate(R.id.action_otpVerifyFragment_to_notesFragment)
                    }

                    null -> {
                        binding.progressBar.gone()
                    }
                }
            }
        }
    }

    private fun initClickListeners() {
        binding.btnContinue.setOnClickListener {
            val otp = binding.etOtp.text
            if (otp.isNotEmpty()) {
                viewModel.verifyOtp(VerifyOtpRequest("$countryCode$number", otp.toString()))
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.text_please_enter_a_valid_otp), Toast.LENGTH_SHORT
                ).show()
            }

        }
        binding.tvTimer.setOnClickListener {
            if (binding.tvTimer.text == getString(R.string.text_retry)) {
                findNavController().popBackStack()
            }
        }
        binding.ivEdit.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}