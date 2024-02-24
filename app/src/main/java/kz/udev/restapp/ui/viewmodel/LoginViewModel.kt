package kz.udev.restapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.udev.restapp.core.util.Resource
import kz.udev.restapp.domain.use_case.LoginUseCase
import kz.udev.restapp.ui.state.LoginState

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    fun onTypeUsername(value: String) {
        _username.value = value
    }

    fun onTypePassword(value: String) {
        _password.value = value
    }

    fun login() {
        loginUseCase(_username.value, _password.value).onEach { res ->
            when (res) {
                is Resource.Error -> {
                    _loginState.value = LoginState(error = res.resourceError?.errorRes ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _loginState.value = LoginState(loading = true)
                }
                is Resource.Success -> {
                    _loginState.value = LoginState(user = res.data)
                }
            }
        }.launchIn(viewModelScope)

    }


}