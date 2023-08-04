package com.example.assigaisle.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigaisle.data.local.AuthTokenStore
import com.example.assigaisle.data.models.NotesResponse
import com.example.assigaisle.domain.repository.DataRepository
import com.example.assigaisle.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val authTokenStore: AuthTokenStore
) : ViewModel() {

    private val _notesState: MutableStateFlow<Resource<NotesResponse>> = MutableStateFlow(Resource.Loading())
    val notesState = _notesState.asStateFlow()

    fun getNotes() {
        viewModelScope.launch {
            try {
                val authToken = authTokenStore.getAuthToken()
                val response = dataRepository.getNotes(authToken)
                if (response.isSuccessful) {
                    _notesState.emit(Resource.Success(data = response.body()))
                } else {
                    _notesState.emit(Resource.Error(message = "Some thing went wrong"))
                }

            } catch (e: Exception) {
                _notesState.emit(Resource.Error(message = "${e.message}"))
            }
        }
    }
}