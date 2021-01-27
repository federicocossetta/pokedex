package com.fcossetta.pokedex.main.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.model.Pokemon
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
        val pokemon: Pokemon? = arguments?.get("pokemon") as Pokemon?

        context?.let { updatePokemonInformation(pokemon, it) }


    }

    private fun updatePokemonInformation(pokemon: Pokemon?, context: Context) {
        if (pokemon != null) {
            pokemon_name.text = pokemon.name?.capitalize()
            pokemon_number.text = pokemon.id.toString().padStart(3,'0').padStart(4,'#')
            Glide.with(context)
                .load(pokemon.sprites?.front_default)
                .into(pokemon_image)
        }
    }
}
