package com.fcossetta.pokedex.main.ui

import android.annotation.SuppressLint
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
import com.fcossetta.pokedex.main.data.model.StatInfo
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PokeDetailFragment : Fragment() {
    private val viewModel: PokemonViewModel by sharedViewModel()
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemon: Pokemon? = arguments?.get("pokemon") as Pokemon?

        context?.let { updatePokemonInformation(pokemon, it) }


    }

    @SuppressLint("SetTextI18n")
    private fun updatePokemonInformation(pokemon: Pokemon?, context: Context) {
        if (pokemon != null) {
            pokemon_name.text = pokemon.name?.capitalize()
            val padStart = pokemon.id.toString().padStart(3, '0')
            pokemon_number.text = "#$padStart"
            Glide.with(context)
                .load(pokemon.sprites?.front_default)
                .into(pokemon_image)
            if (pokemon.stats != null) {
                for (item: StatInfo in pokemon.stats) {
                    val name: String? = item.stat!!.name
                    when (name) {
                        "attack" -> attack.text = item.baseStats?.toString()
                        "defense" -> defense.text = item.baseStats?.toString()
                        "special-attack" -> special_attack.text = item.baseStats?.toString()
                        "special-defense" -> special_defense.text = item.baseStats?.toString()
                        "speed" -> speed.text = item.baseStats?.toString()
                    }
                }
            }
        }
    }
}
