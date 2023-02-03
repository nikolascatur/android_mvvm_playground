package com.ruangguru.mvvmplaygroundarena.data

import com.ruangguru.mvvmplaygroundarena.data.remote.flower.FlowerService
import com.ruangguru.mvvmplaygroundarena.data.remote.model.response.Plant

class FlowerRepository(private val flowerService: FlowerService) {

    suspend fun fetchAllPlant(): List<Plant>? {
        return flowerService.getAllPlant().data
    }
}