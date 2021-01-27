package com.fcossetta.pokedex.main.data

import androidx.paging.PagingData
import com.fcossetta.pokedex.main.data.model.Pokemon
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import io.uniflow.core.flow.data.UIEvent
import kotlinx.coroutines.flow.Flow

open class PokemonEvent : UIEvent() {
    data class PokemonFound(val pokemon: Pokemon) :
        PokemonEvent()
    data class PokemonListFound(val pokemon: Flow<PagingData<SimplePokemon>>) :
        PokemonEvent()

}