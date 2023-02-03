package com.ruangguru.mvvmplaygroundarena.data.remote.flower

import com.ruangguru.mvvmplaygroundarena.data.remote.model.response.ListPlant
import retrofit2.http.GET

interface FlowerWebservice {

    @GET("api/plant")
    suspend fun getAllPlant(): ListPlant
}