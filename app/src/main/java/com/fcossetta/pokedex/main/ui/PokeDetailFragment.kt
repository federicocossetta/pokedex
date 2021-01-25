package com.fcossetta.pokedex.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.PokemonViewState
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PokeDetailFragment : Fragment() {
    private val viewModel: PokemonViewModel by sharedViewModel()
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStates(viewModel) { state ->
            when (state) {
                is PokemonViewState.PokemonDetail -> updatePokemonInformation(state, view)
            }
        }

    }

    private fun updatePokemonInformation(state: PokemonViewState.PokemonDetail, view: View) {
        pokemon_name.text = state.pokemon.name
    }
}
