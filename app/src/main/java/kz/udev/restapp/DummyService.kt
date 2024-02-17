package kz.udev.restapp

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyService {

    @GET("products")
    suspend fun getProducts(

    ) : ProductsResponse

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ) : Product

    @GET("products/search")
    suspend fun getSearchProducts(
        @Header("Authorization") auth: String,
        @Query("q") search: String
    ) : ProductsResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ) : LoginResponse

}