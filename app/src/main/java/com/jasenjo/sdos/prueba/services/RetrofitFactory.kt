package com.jasenjo.sdos.prueba.services

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    const val FRUIT_SERVICE_BASE_URL = "https://data.ct.gov/resource/hma6-9xbg.json/"

    fun getRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FRUIT_SERVICE_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

}