package com.example.assigaisle.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.assigaisle.R
import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.databinding.FragmentLoginBinding
import com.example.assigaisle.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect{
                when(it){
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT).show()

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val countryCode=binding.etCountryCode.text
                        val mobileNumber=binding.etMobileNumber.text
                        val bundle=Bundle()
                        bundle.putString(MOBILE_NUMBER,"$countryCode $mobileNumber")

                        findNavController().navigate(R.id.action_loginFragment_to_otpVerifyFragment)
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun initClickListeners() {
        binding.btnContinue.setOnClickListener{
            val countryCode=binding.etCountryCode.text
            val mobileNumber=binding.etMobileNumber.text
            if (countryCode.isNotEmpty() && mobileNumber.isNotEmpty()){
                viewModel.login(LoginRequest("$countryCode$mobileNumber"))
            }else{
                Toast.makeText(
                    requireContext(),
                    getString(R.string.text_enter_valid_mobile),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object{
        const val MOBILE_NUMBER="mobile_number"
    }
}