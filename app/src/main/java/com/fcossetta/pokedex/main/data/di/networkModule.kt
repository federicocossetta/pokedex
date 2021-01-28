package com.fcossetta.pokedex.main.data.di

import com.fcossetta.pokedex.main.data.api.provideApi
import com.fcossetta.pokedex.main.data.api.provideDefaultOkhttpClient
import com.fcossetta.pokedex.main.data.api.provideRetrofit
import org.koin.dsl.module

val networkModule = module   {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(client = get()) }
    single { provideApi(get()) }
}