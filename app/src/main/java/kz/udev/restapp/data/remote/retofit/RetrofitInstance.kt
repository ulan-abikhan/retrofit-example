package kz.udev.restapp.data.remote.retofit

import kz.udev.restapp.core.util.Constants
import kz.udev.restapp.data.remote.service.DummyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private var retrofitInstance: Retrofit? = null

    var dummyService: DummyService? = null
        private set

    init {
        if (retrofitInstance == null || dummyService == null) {

            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

            retrofitInstance = Retrofit
                .Builder()
                .client(client)
                .baseUrl(Constants.dummyJsonBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            dummyService = retrofitInstance?.create(DummyService::class.java)
        }

    }


//    private fun dummyService(): DummyService {
//        if (retrofitInstance == null || dummyService == null) {
//
//            val logging = HttpLoggingInterceptor()
//
//            logging.level = HttpLoggingInterceptor.Level.BODY
//
//            val client = OkHttpClient
//                .Builder()
//                .addInterceptor(logging)
//                .build()
//
//            retrofitInstance = Retrofit
//                .Builder()
//                .client(client)
//                .baseUrl(Constants.dummyJsonBaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            dummyService = retrofitInstance?.create(DummyService::class.java)
//        }
//
//        return dummyService!!
//    }



}