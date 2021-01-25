package com.fcossetta.pokedex.main.data.api

class ApiHelper(private val apiService: PokeService) {

    fun getPokemon(idOrName: String) = apiService.getPokemon(idOrName)
    suspend fun getPokemonList(limit: Int, offset: Int) = apiService.getPokemonList(limit, offset)
}