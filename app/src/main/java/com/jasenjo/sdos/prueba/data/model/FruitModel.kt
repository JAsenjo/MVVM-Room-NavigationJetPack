package com.jasenjo.sdos.prueba.data.model

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com
*/
data class FruitModel(
    @SerializedName("farmer_id") val farmerId: Int,
    @SerializedName("category") val category: String,
    @SerializedName("item") val item: String,
    @SerializedName("phone1") val phone: String)