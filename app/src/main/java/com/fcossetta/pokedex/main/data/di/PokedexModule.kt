package com.fcossetta.pokedex.main.data.di

import androidx.room.Room
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val myModule: Module = module {
    viewModel { PokemonViewModel(get()) }
    single { NetworkListener(androidContext(), get()) }

}

