package com.fcossetta.pokedex.main.data

import androidx.paging.PagingData
import com.fcossetta.pokedex.main.data.model.Pokemon
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow

open class PokemonViewState : UIState() {
    data class PokemonDetail(val pokemon: Pokemon) :
        PokemonViewState()

    data class PokemonList(val pokemon: Flow<PagingData<SimplePokemon>>) :
        PokemonViewState()
}