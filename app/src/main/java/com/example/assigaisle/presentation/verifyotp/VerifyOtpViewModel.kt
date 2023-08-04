package com.example.assigaisle.presentation.verifyotp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigaisle.data.local.AuthTokenStore
import com.example.assigaisle.data.models.VerifyOtpRequest
import com.example.assigaisle.domain.repository.DataRepository
import com.example.assigaisle.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class VerifyOtpViewModel @Inject constructor(private val dataRepository: DataRepository,private val authTokenStore: AuthTokenStore): ViewModel() {

    private val _verifyOtpState: MutableStateFlow<Resource<String>?> = MutableStateFlow(null)
    val verifyOtpState = _verifyOtpState.asStateFlow()
    fun verifyOtp(verifyOtpRequest: VerifyOtpRequest){
        viewModelScope.launch {
            _verifyOtpState.emit(Resource.Loading())
            try {
                val response=dataRepository.verifyOtp(verifyOtpRequest)
                if (response.isSuccessful){
                    response.body()?.authToken?.let { saveAuthToken(it) }
                    _verifyOtpState.emit(Resource.Success(data = response.body()?.authToken))
                }else{
                    _verifyOtpState.emit(Resource.Error(message = "Some thing went wrong"))
                }

            }catch (e:Exception){
                _verifyOtpState.emit(Resource.Error(message = "${e.message}"))
            }
        }
    }

    private fun saveAuthToken(token:String){
        runBlocking {
            authTokenStore.saveAuthToken(token)
        }
    }
}