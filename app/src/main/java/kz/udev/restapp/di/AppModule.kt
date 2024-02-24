package kz.udev.restapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.udev.restapp.core.util.Constants
import kz.udev.restapp.data.remote.service.DummyService
import kz.udev.restapp.data.repository.DummyRepositoryImpl
import kz.udev.restapp.domain.repository.DummyRepository
import kz.udev.restapp.domain.use_case.LoginUseCase
import kz.udev.restapp.ui.viewmodel.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDummyService() : DummyService {
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient
            .Builder()
            .addInterceptor(logging)
            .build()

        val retrofitInstance = Retrofit
            .Builder()
            .client(client)
            .baseUrl(Constants.dummyJsonBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitInstance.create(DummyService::class.java)
    }

    @Provides
    @Singleton
    fun provideDummyRepository(dummyService: DummyService): DummyRepository {
        return DummyRepositoryImpl(dummyService)
    }

    @Provides
    @Singleton
    fun provideLoginViewModel(loginUseCase: LoginUseCase) : LoginViewModel {
        return LoginViewModel(loginUseCase)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(dummyRepository: DummyRepository): LoginUseCase {
        return LoginUseCase(dummyRepository)
    }


}