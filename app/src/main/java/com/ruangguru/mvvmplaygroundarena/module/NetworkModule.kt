package com.ruangguru.mvvmplaygroundarena.module

import com.ruangguru.mvvmplaygroundarena.data.remote.flower.FlowerService
import com.ruangguru.mvvmplaygroundarena.data.remote.flower.FlowerWebservice
import com.ruangguru.mvvmplaygroundarena.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        OkHttpClient()
            .newBuilder()
            .connectTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    }

    single {
        FlowerService(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://nicolascaturblog.vercel.app/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(FlowerWebservice::class.java)
    }

}