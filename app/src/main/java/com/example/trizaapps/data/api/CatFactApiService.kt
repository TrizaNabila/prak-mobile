package com.example.trizaapps.data.api

import com.example.trizaapps.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    // Mengambil data dari endpoint "fact" dengan metode HTTP GET
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}