package kz.udev.restapp.domain.repository

import kz.udev.restapp.data.remote.dto.body.LoginResponse

interface DummyRepository {

    suspend fun login(username: String, password: String) : LoginResponse

}