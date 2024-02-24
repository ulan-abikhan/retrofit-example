package kz.udev.restapp.core.util

sealed class Resource<T>(val data: T? = null, val resourceError: ResourceError? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(resourceError: ResourceError, data: T? = null): Resource<T>(data, resourceError)

}

data class ResourceError(
    val message: String,
    val errorCode: Int,
    val errorRes: String
)