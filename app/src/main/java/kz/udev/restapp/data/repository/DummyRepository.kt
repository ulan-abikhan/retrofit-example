package kz.udev.restapp.data.repository

import kz.udev.restapp.data.remote.dto.body.LoginBody
import kz.udev.restapp.data.remote.dto.body.LoginResponse
import kz.udev.restapp.data.remote.service.DummyService

class DummyRepository(
    private val dummyService: DummyService
) {

    suspend fun login(username: String, password: String) : LoginResponse {
        return dummyService.login(LoginBody(username, password))
    }

}