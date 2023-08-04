package com.example.assigaisle.presentation.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigaisle.data.models.LoginRequest
import com.example.assigaisle.domain.repository.DataRepository
import com.example.assigaisle.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataRepository: DataRepository):ViewModel() {

    private val _loginState: MutableStateFlow<Resource<Boolean>?> = MutableStateFlow(null)
    val loginState = _loginState.asStateFlow()
    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            _loginState.emit(Resource.Loading())
            try {
                val response=dataRepository.login(loginRequest)
                if (response.isSuccessful && response.body()?.status==true){
                    _loginState.emit(Resource.Success(data = true))
                }else{
                    _loginState.emit(Resource.Error(message = "Some thing went wrong"))
                }

            }catch (e:Exception){
                _loginState.emit(Resource.Error(message = "${e.message}"))
            }
        }
    }

    fun resetState(){
        viewModelScope.launch {
            _loginState.emit(null)
        }
    }
}