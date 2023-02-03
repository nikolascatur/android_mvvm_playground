package com.ruangguru.mvvmplaygroundarena.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Plant(
    @SerializedName("plantId")
    val plantId: Int? = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("growZoneNumber")
    val growZoneNumber: Int? = 0,
    @SerializedName("wateringInterval")
    val wateringInterval: Int? = 0,
    @SerializedName("imageUrl")
    val imageUrl: String? = null
)

data class ListPlant(
    @SerializedName("data")
    val data: List<Plant>? = emptyList()
)

