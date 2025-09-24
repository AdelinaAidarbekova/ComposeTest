package com.example.test.di

import androidx.room.Room
import com.example.test.data.RickApi
import com.example.test.data.repository.RickRepositoryImpl
import com.example.test.data.room.AppDatabase
import com.example.test.domain.RickRepository
import com.example.test.domain.usecase.GetCharactersUseCase
import com.example.test.presentation.CharactersViewModel
import com.example.test.utils.Const.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

val appModule = module {
	single { getRetrofitInstance(get()) }
	
	single { getOkHttpClient() }
	
	single<RickApi> { getRickApi(retrofit = get()) }
	
	single<RickRepository> { RickRepositoryImpl(get(), get()) }
	factory { GetCharactersUseCase(get()) }
	viewModel { CharactersViewModel(get()) }
	single {
		Room.databaseBuilder(get(), AppDatabase::class.java, "app_db").build()
	}
	single { get<AppDatabase>().characterDao() }
}

fun getRickApi(retrofit: Retrofit): RickApi {
	return retrofit.create(RickApi::class.java)
}

fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
	return Retrofit.Builder()
		.baseUrl(BASE_URL)
		.client(okHttpClient)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
}

fun getOkHttpClient(): OkHttpClient {
	return OkHttpClient.Builder()
		.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
		.connectTimeout(30, TimeUnit.SECONDS)
		.readTimeout(30, TimeUnit.SECONDS)
		.build()
}