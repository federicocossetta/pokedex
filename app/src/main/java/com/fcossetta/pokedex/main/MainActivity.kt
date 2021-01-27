package com.fcossetta.pokedex.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.PokemonEvent
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.PokemonViewState
import com.fcossetta.pokedex.main.ui.MainFragmentDirections
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    val TAG = "TEST"

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController
        onStates(viewModel) { state ->
            Log.d(TAG, state.toString());
            when (state) {
                is UIState.Empty -> viewModel.getPokemonList(100)
                is PokemonViewState.PokemonDetailRequest -> viewModel.findPokemon(state.pokemon)
            }
        }

        onEvents(viewModel) {
            when (val event = it.peek()) {
                is PokemonEvent.PokemonFound -> {
//                    navController.navigate(R.id.main_to_pokemon_detail)
                    val mainToPokemonDetail =
                        MainFragmentDirections.mainToPokemonDetail(event.pokemon)
                    navController.navigate(mainToPokemonDetail)

                }
                is PokemonEvent.PokemonListFound ->
                    navController.navigate(R.id.mainFragment)
            }
        }

    }
}