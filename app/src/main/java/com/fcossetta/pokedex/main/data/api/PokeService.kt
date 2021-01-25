package com.fcossetta.pokedex.main.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {

    @GET("/api/v2/pokemon/{value}")
    fun getPokemon(@Path("value") property: String?): Call<ResponseBody>

    @GET("/api/v2/pokemon")
     fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<ResponseBody>
}