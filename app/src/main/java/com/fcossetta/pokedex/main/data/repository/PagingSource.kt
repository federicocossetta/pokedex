package com.fcossetta.pokedex.main.data.repository

import androidx.paging.PagingSource
import com.fcossetta.pokedex.main.data.api.PokeService
import com.fcossetta.pokedex.main.data.model.PokemonResult
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.await

class PagingSource(
    private val api: PokeService, private val limit: Int,
) : PagingSource<Int, SimplePokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimplePokemon> {
        var values: List<SimplePokemon> = emptyList()
        try {
            if (params.key != null) {
                val test = params.key?.minus(2) ?: 0 * limit;
                val result = api.getPokemonList(limit, test).await()
                val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val adapter: JsonAdapter<PokemonResult>? = moshi.adapter(PokemonResult::class.java)
                val jsonString = result.string()
                val fromJson = adapter?.fromJson(jsonString)
                if (fromJson?.results != null)
                    values = fromJson.results
            }
        } catch (e: Exception) {
            Error("Error", e)

        }
        return LoadResult.Page(
            data = values,
            prevKey = params.key,
            nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
        )
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}