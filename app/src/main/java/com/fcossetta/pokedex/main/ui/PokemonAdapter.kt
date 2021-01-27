package com.fcossetta.pokedex.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.MainActivity
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.PokemonViewState
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import kotlinx.android.synthetic.main.pokemon_item_list.view.*

class PokemonAdapter() :
    PagingDataAdapter<SimplePokemon, PokemonAdapter.MyViewHolder>(DiffUtilCallBack()) {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item_list, parent, false)
        return MyViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MyViewHolder(
        itemView: View,
        var context: Context?,
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(pokemon: SimplePokemon) {

            itemView.pokemon_name_compact.text = pokemon.name
            val pokenumber = pokemon.url?.let {
                it.substring(it.indexOf("pokemon/") + 8, pokemon.url.length - 1).padStart(3, '0')
            }
            itemView.pokemon_number_compact.text = "#$pokenumber"
            itemView.setOnClickListener {
                if (pokemon.url != null && context != null) {

                    val pokemonViewModel =
                        ViewModelProviders.of(context as MainActivity)[PokemonViewModel::class.java]
                    pokemonViewModel.action {
                        setState {
                            PokemonViewState.PokemonDetailRequest(
                                pokemon.url
                            )
                        }
                    }
                }
            }
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<SimplePokemon>() {
    override fun areItemsTheSame(
        oldItem: SimplePokemon,
        newItem: SimplePokemon,
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: SimplePokemon,
        newItem: SimplePokemon,
    ): Boolean {
        return oldItem == newItem
    }
}