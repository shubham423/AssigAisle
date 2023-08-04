package com.example.assigaisle.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.assigaisle.R
import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.databinding.FragmentLoginBinding
import com.example.assigaisle.utils.Resource
import com.example.assigaisle.utils.gone
import com.example.assigaisle.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
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
                        binding.progressBar.gone()
                        Toast.makeText(requireContext(),
                            getString(R.string.text_something_went_wrong), Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        val countryCode=binding.etCountryCode.text
                        val mobileNumber=binding.etMobileNumber.text
                        val bundle=Bundle()
                        bundle.putString(MOBILE_NUMBER,mobileNumber.toString())
                        bundle.putString(COUNTRY_CODE,countryCode.toString())
                        findNavController().navigate(R.id.action_loginFragment_to_otpVerifyFragment,bundle).also {
                            viewModel.resetState()
                        }
                    }
                    else -> {
                        binding.progressBar.gone()
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
        const val COUNTRY_CODE="country_code"
    }
}