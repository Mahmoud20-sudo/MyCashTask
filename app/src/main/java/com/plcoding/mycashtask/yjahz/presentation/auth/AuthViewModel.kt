package com.plcoding.mycashtask.yjahz.presentation.auth

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.user.UserModel
import com.plcoding.mycashtask.yjahz.domain.use_case.SellersUseCases
import com.plcoding.mycashtask.yjahz.domain.use_case.UsersUseCases
import com.plcoding.mycashtask.yjahz.presentation.model.UiEvent
import com.plcoding.mycashtask.yjahz.util.Constants.emailAddressRegex
import com.plcoding.mycashtask.yjahz.util.isPhoneNumberValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel //
class AuthViewModel @Inject constructor(
    private val userUseCases: UsersUseCases?,
) : ViewModel() {

    var _name by mutableStateOf("")
        private set
    var _email by mutableStateOf("")
        private set
    var _phone by mutableStateOf("")
        private set
    var _password by mutableStateOf("")
        private set
    var _confirmPass by mutableStateOf("")
        private set

    private val _userFlow = MutableStateFlow<Resource<UserModel>>(Resource.Loading())
    val userFlow = _userFlow

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setName(value: String) {
        _name = value
    }

    fun setEmail(value: String) {
        _email = value
    }

    fun setPhone(value: String) {
        _phone = value
    }

    fun setPassword(value: String) {
        _password = value
    }

    fun setConfirmPassword(value: String) {
        _confirmPass = value
    }

    fun login(email: String, password: String): Boolean {
        var failingReason = ""
        var loginStatus = true

        if (email.isBlank() || password.isBlank()) {
            failingReason = "Please fill all fields!"
            loginStatus = false
        } else if (password.chars().count() < 8) {
            failingReason = "Please choose a longer password!"
            loginStatus = false
        } else if (!email.matches(emailAddressRegex)) {
            failingReason = "Please typing invalid email!"
            loginStatus = false
        }

        if (!loginStatus) {
            viewModelScope.launch {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = failingReason
                    )
                )
            }
        } else {
            remoteLogin(email, password)
        }

        return loginStatus
    }

    private fun remoteLogin(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = userUseCases?.login?.invoke(email.trim(), password)
            result?.collect {
                renderResults(it)
            }
        }
    }

    fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        confirm: String
    ): Boolean {
        var failingReason = ""
        var loginStatus = true

        if (email.isBlank() || password.isBlank() || name.isBlank() || phone.isBlank() || confirm.isBlank()) {
            failingReason = "Please fill all fields!"
            loginStatus = false
        } else if (password.chars().count() < 8) {
            failingReason = "Please choose a longer password!"
            loginStatus = false
        } else if (password != confirm) {
            failingReason = "Password not matches with confirm password!"
            loginStatus = false
        } else if (!email.trim().matches(emailAddressRegex)) {
            failingReason = "Please typing a valid email!"
            loginStatus = false
        }

        if (!loginStatus) {
            viewModelScope.launch {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = failingReason
                    )
                )
            }
        } else {
            remoteRegister(name, email, phone, password)
        }

        return loginStatus
    }

    private fun remoteRegister(name: String, email: String, phone: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = userUseCases?.register?.invoke(
                name = name.trim(),
                password = password,
                email = email.trim(),
                phone = phone
            )
            result?.collect {
                renderResults(it)
            }
        }
    }

    private fun renderResults(res: Resource<UserModel>) {
        when (res) {
            is Resource.Error -> {
                //FAIL
                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = res.error?.message ?: "Couldn't retrieve sellers"
                        )
                    )
                }
                _isLoading.value = false
            }

            is Resource.Loading -> {
                //LOADING
                _isLoading.value = true
            }

            is Resource.Success -> {
                _userFlow.value = res
                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.NavigateToNext
                    )
                }
                _isLoading.value = false
            }
        }
    }
}