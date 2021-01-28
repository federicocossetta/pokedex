package com.fcossetta.pokedex.main.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.model.Pokemon
import com.fcossetta.pokedex.main.data.model.StatInfo
import com.fcossetta.pokedex.main.data.model.PokemonType
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import java.util.*

class PokeDetailFragment : Fragment() {
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
            pokemon_name.text = pokemon.name?.capitalize(Locale.ROOT)
            val padStart = pokemon.id.toString().padStart(3, '0')
            pokemon_number.text = "#$padStart"
            Glide.with(context)
                .load(pokemon.sprites?.front_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pokemon_image)
            if (pokemon.stats != null) {
                for (item: StatInfo in pokemon.stats) {
                    val name: String? = item.stat!!.name
                    when (name) {
                        "hp" -> hp.text = item.baseStats?.toString()
                        "attack" -> attack.text = item.baseStats?.toString()
                        "defense" -> defense.text = item.baseStats?.toString()
                        "special-attack" -> special_attack.text = item.baseStats?.toString()
                        "special-defense" -> special_defense.text = item.baseStats?.toString()
                        "speed" -> speed.text = item.baseStats?.toString()
                    }
                }
            }
            pokemon.weight?.let { weight.text = it.toString() }
            pokemon.height?.let { height.text = it.toString() }
            if (pokemon.pokemonTypes != null) {
                val ids = mutableListOf<Int>()
                for (item: PokemonType in pokemon.pokemonTypes) {

                    val cardView = CardView(context)
                    cardView.id = View.generateViewId()
                    cardView.cardElevation = 10F
                    cardView.layoutParams =
                        RelativeLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT)
                    cardView.radius = 10F
                    val typeText = TextView(context)
                    typeText.layoutParams =
                        RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                    typeText.gravity = Gravity.CENTER
                    typeText.text = item.typeDetail?.name
                    cardView.addView(typeText)
                    container.addView(cardView)
                    ids.add(cardView.id)
                }
                types_flow.referencedIds = ids.toIntArray()
            }
        }
    }
}
