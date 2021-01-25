package com.fcossetta.pokedex.main

import android.app.Application
import com.fcossetta.pokedex.main.data.api.networkModule
import com.fcossetta.pokedex.main.utils.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokedexApplication.applicationContext)
            modules(listOf(networkModule, myModule))
        }
    }
}