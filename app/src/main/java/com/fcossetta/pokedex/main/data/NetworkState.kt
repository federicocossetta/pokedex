package com.fcossetta.pokedex.main.data

import androidx.paging.PagingData
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow

open class NetworkState : UIState() {

    data class NetworkChanged(val online: Boolean) :
        NetworkState()

}