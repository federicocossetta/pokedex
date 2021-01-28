package com.fcossetta.pokedex.main.data.di

import com.fcossetta.pokedex.main.data.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val myModule: Module = module {
    single { PokemonViewModel(get()) }
}

