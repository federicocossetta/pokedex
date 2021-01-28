package com.fcossetta.pokedex.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.NetworkState
import com.fcossetta.pokedex.main.data.PokemonEvent
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.ui.LoadingFragmentDirections
import com.fcossetta.pokedex.main.ui.MainFragmentDirections
import com.fcossetta.pokedex.main.utils.NetworkListener
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var recover: Boolean = false
    private lateinit var navController: NavController

    val TAG = "TEST"

    private val viewModel: PokemonViewModel by viewModel()
    private val networkListener: NetworkListener by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController
        onStates(viewModel) { state ->
            if (networkListener.online) {
                when (state) {
                    is NetworkState.NetworkChanged -> if (state.online) {
                        viewModel.getPokemonList(100)
                    }
                }
            } else {
                Toast.makeText(applicationContext, R.string.offline, Toast.LENGTH_SHORT).show()
                recover = true;
            }
        }
        onEvents(viewModel) { it ->
                when (val peek = it.take()) {
                    is PokemonEvent.PokemonFound -> {
                            it.take()
                            val mainToPokemonDetail =
                                MainFragmentDirections.mainToPokemonDetail()
                            navController.navigate(mainToPokemonDetail)
                    }
                    is PokemonEvent.PokemonDetailRequest -> {

                            it.take()
                            viewModel.findPokemon(peek.pokemonUrl)
                    }
                    is PokemonEvent.PokemonListFound -> {
                        val mainToPokemonDetail =
                            LoadingFragmentDirections.actionLoadingToMain()
                        navController.navigate(mainToPokemonDetail)
                    }
                }
        }
    }


}