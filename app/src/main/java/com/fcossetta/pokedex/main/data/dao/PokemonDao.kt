package com.fcossetta.pokedex.main.data.dao

import androidx.room.*
import com.fcossetta.pokedex.main.data.model.Pokemon
import com.fcossetta.pokedex.main.data.model.SimplePokemon

@Dao
interface PokemonDao {
    @Query("SELECT *  FROM SimplePokemon WHERE limitSearch like :limit AND indexSearch like :index")
    fun getAllSimple(limit: Int, index: Int): List<SimplePokemon>

    @Insert
    fun insertAll(pokemons: List<SimplePokemon>)

    @Query("DELETE FROM SimplePokemon WHERE limitSearch like :limit AND indexSearch like :index")
    fun deleteAll(limit: Int, index: Int)


    @Query("SELECT * FROM pokemon WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE url LIKE :url")
    fun findPokemonByUrl(url: String): Pokemon

    @Insert
    fun insertPokemon(pokemon: Pokemon)

    @Delete
    fun deleteAll(vararg pokemon: Pokemon)

    @Delete
    fun delete(pokemon: Pokemon)
}