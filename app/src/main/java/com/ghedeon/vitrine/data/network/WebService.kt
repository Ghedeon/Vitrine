package com.ghedeon.vitrine.data.network

import com.ghedeon.vitrine.data.network.model.PersonasPageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("character")
    suspend fun getPersonas(@Query("page") page: Int): PersonasPageDto
}