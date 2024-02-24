package kz.udev.restapp.ui.state

import kz.udev.restapp.domain.model.User

data class LoginState(
    val loading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)
