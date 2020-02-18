package com.jasenjo.sdos.prueba.services.interfaces

import com.jasenjo.sdos.prueba.data.model.FruitModel
import io.reactivex.Observable
import retrofit2.http.GET

interface FruitService {

    @GET("?category=fruit")
    fun getFruits(): Observable<List<FruitModel>>
}