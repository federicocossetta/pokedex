package com.fcossetta.pokedex.main.data

import androidx.paging.PagingData
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow

open class PokemonViewState : UIState() {

    data class PokemonList(val pokemon: Flow<PagingData<SimplePokemon>>) :
        PokemonViewState()

    data class PokemonDetailRequest(val pokemon: String) :
        PokemonViewState()
}