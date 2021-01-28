package com.fcossetta.pokedex.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.PokemonEvent
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.PokemonViewState
import com.fcossetta.pokedex.main.data.database.AppDatabase
import com.fcossetta.pokedex.main.data.model.Pokemon
import com.fcossetta.pokedex.main.ui.LoadingFragmentDirections
import com.fcossetta.pokedex.main.ui.MainFragmentDirections
import com.fcossetta.pokedex.main.utils.NetworkListener
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var navController: NavController

    val TAG = "TEST"

    private val viewModel: PokemonViewModel by viewModel()
    private val networkListener: NetworkListener by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "pokemon-db"
        ).build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController
        onStates(viewModel) { state ->
            Log.d(TAG, state.toString());
            when (state) {
                is UIState.Empty -> {
                    viewModel.getPokemonList(100, this, db)
                }
                is PokemonViewState.PokemonDetailRequest -> {
                    if (networkListener.online) {
                        viewModel.findPokemon(state.pokemonUrl)
                    } else {
                        val findPokemonByUrl: Pokemon =
                            db.pokemonDao().findPokemonByUrl(state.pokemonUrl)
                        viewModel.action {
                            sendEvent {
                                PokemonEvent.PokemonFromCache(findPokemonByUrl)
                            }
                        }
                    }
                }
            }
        }

        onEvents(viewModel) {
            when (val event = it.peek()) {
                is PokemonEvent.PokemonFound -> {
                    db.pokemonDao().insertPokemon(event.pokemon)
                    val mainToPokemonDetail =
                        MainFragmentDirections.mainToPokemonDetail(event.pokemon)
                    navController.navigate(mainToPokemonDetail)

                }
                is PokemonEvent.PokemonFromCache -> {
                    val mainToPokemonDetail =
                        MainFragmentDirections.mainToPokemonDetail(event.pokemon)
                    navController.navigate(mainToPokemonDetail)

                }
                is PokemonEvent.PokemonListFound -> {
                    val actionLoadingToMain = LoadingFragmentDirections.actionLoadingToMain()
                    navController.navigate(actionLoadingToMain)
                }
            }

        }
    }

}