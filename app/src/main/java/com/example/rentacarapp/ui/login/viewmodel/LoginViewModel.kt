package com.example.rentacarapp.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentacarapp.usecase.login.CustomerLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: CustomerLoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(newEmail: String){
        _uiState.update { it.copy(email = newEmail, errorMessage = null) }
    }

    fun onPasswordChanged(newPassword: String){
        _uiState.update { it.copy(password = newPassword, errorMessage = null) }
    }

    fun onLoginClicked(){
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val currentEmail = uiState.value.email
            val currentPassword = uiState.value.password
            val result = login(currentEmail,currentPassword)
            if(result.isSuccess){
                _uiState.update { it.copy(isLoading = false, success = true) }
            }else{
                val error = result.exceptionOrNull()?.message?:"Error"
                _uiState.update { it.copy(isLoading = false, errorMessage = error) }
            }
        }
    }

    fun resetLoginState(){
        _uiState.update { it.copy(success = false) }
    }
}