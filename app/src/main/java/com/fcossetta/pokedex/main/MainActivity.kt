package com.fcossetta.pokedex.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.PokemonViewState
import io.uniflow.androidx.flow.onStates
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    //    private lateinit var navController: NavController
    val TAG = "TEST"

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController
        navController.navigate(R.id.mainFragment2)
        onStates(viewModel) { state ->
            Log.d(TAG, state.toString());
            when (state) {
                is PokemonViewState.PokemonDetail -> showPokemonDetail()
            }
        }

    }

    private fun showPokemonDetail() {
        navController.navigate(R.id.main_to_pokemon_detail)
    }
}