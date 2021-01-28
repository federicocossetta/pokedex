package com.fcossetta.pokedex.main

import android.app.Application
import com.fcossetta.pokedex.main.data.di.NetworkReactModule
import com.fcossetta.pokedex.main.data.di.myModule
import com.fcossetta.pokedex.main.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokedexApplication.applicationContext)
            modules(listOf(networkModule, myModule, NetworkReactModule))
        }
    }
}