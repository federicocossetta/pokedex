package com.fcossetta.pokedex.main.utils

import com.fcossetta.pokedex.main.data.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val myModule: Module = module {
    viewModel { PokemonViewModel(get()) }
}

