package com.ruangguru.mvvmplaygroundarena.data.remote.flower

import com.ruangguru.mvvmplaygroundarena.data.remote.model.response.ListPlant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlowerService(private val flowerWebservice: FlowerWebservice) {

    suspend fun getAllPlant(): ListPlant = withContext(Dispatchers.Default) {
        val result = flowerWebservice.getAllPlant()
        result
    }

}