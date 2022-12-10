package com.cezila.essential.data.remote

import com.cezila.essential.data.remote.dto.DrinkDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EssentialApi {

    @GET("/drinks")
    suspend fun getDrinks(): List<DrinkDto>

    @GET("/drinks")
    suspend fun getDrinksById(
        @Query("id") id: Int
    ): DrinkDto

    companion object {
        const val BASE_URL_PROD = "https://essential-api-production.up.railway.app"
        const val BASE_URL_LOCAL = "192.168.15.17"
    }
}