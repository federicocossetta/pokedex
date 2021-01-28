package com.fcossetta.pokedex.main.data.di

import com.fcossetta.pokedex.main.utils.NetworkListener
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val NetworkReactModule: Module = module {
    single { NetworkListener(androidContext(), get()) }
}

