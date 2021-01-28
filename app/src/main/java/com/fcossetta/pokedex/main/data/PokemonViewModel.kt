package com.fcossetta.pokedex.main.data

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fcossetta.pokedex.main.MainActivity
import com.fcossetta.pokedex.main.data.api.PokeService
import com.fcossetta.pokedex.main.data.database.AppDatabase
import com.fcossetta.pokedex.main.data.model.Pokemon
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import com.fcossetta.pokedex.main.data.repository.PagingSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.await


class PokemonViewModel(private val api: PokeService) :
    AndroidDataFlow(defaultState = UIState.Empty) {

    private lateinit var pager: Pager<Int, SimplePokemon>
    fun findPokemon(fullUrl: String) = action {
        val response =
            withContext(Dispatchers.IO) { api.retrivePokemonData(fullUrl).await() }
        emitPokeDetail(response, fullUrl)

    }

    private fun emitPokeDetail(response: ResponseBody, url: String) {
        val rawPokemon: String? = response.string()
        if (rawPokemon != null) {
            val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter = moshi.adapter(Pokemon::class.java)
            val pokemon: Pokemon? = adapter.fromJson(rawPokemon)
            if (pokemon != null) {
                pokemon.url = url
                action { sendEvent { PokemonEvent.PokemonFound(pokemon) } }
            }
        }
    }


    fun getPokemonList(limit: Int, mainActivity: MainActivity, appDatabase: AppDatabase) =
        action {
            pager = Pager(
                config = PagingConfig(pageSize = 20, maxSize = 500), null,
                pagingSourceFactory = { PagingSource(api, limit, mainActivity, appDatabase) }
            )

            sendEvent { (PokemonEvent.PokemonListFound(pager.flow.cachedIn(viewModelScope))) }
        }
}

