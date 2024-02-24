package kz.udev.restapp.data.remote.dto.body

import kz.udev.restapp.domain.model.User

data class LoginBody(
    val username: String,
    val password: String
)

data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String
) {

    fun toUser() : User {
        return User(id, firstName, lastName, image, token)
    }

}