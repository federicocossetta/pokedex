package com.fcossetta.pokedex.main.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.fcossetta.pokedex.main.data.NetworkState
import com.fcossetta.pokedex.main.data.PokemonViewModel

class NetworkListener(private val context: Context, val pokemonViewModel: PokemonViewModel) {
    private var receiver: BroadcastReceiver? = null
    private val cm: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    open var online: Boolean = false


    fun registerReceiver() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        if (receiver == null) {
            receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    online = cm.activeNetworkInfo != null
                    pokemonViewModel.action { setState { NetworkState.NetworkChanged(online) } }
                }
            }
            context.registerReceiver(receiver, filter)
        }
    }

    init {
        registerReceiver()
        online = cm.activeNetworkInfo != null
    }
}