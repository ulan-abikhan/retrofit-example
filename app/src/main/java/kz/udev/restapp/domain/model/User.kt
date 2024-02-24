package kz.udev.restapp.domain.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val token: String
)