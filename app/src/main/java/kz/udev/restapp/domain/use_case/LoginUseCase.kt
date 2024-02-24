package kz.udev.restapp.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kz.udev.restapp.core.util.Resource
import kz.udev.restapp.core.util.ResourceError
import kz.udev.restapp.domain.model.User
import kz.udev.restapp.domain.repository.DummyRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    operator fun invoke(username: String, password: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())

            val response = dummyRepository.login(username, password)

            emit(Resource.Success(response.toUser()))

        } catch (e: IOException) {
            emit(Resource.Error(resourceError = ResourceError("No internet", -1, "")))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    resourceError = ResourceError(
                        e.message ?: "",
                        e.code(),
                        e.localizedMessage ?: ""
                    )
                )
            )
        }
    }.flowOn(Dispatchers.IO)

}