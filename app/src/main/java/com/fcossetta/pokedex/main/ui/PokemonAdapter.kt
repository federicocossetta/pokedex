package com.fcossetta.pokedex.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import kotlinx.android.synthetic.main.pokemon_item_list.view.*

class PokemonAdapter() :
    PagingDataAdapter<SimplePokemon, PokemonAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: SimplePokemon) {

            itemView.name.text = character.name
            itemView.setOnClickListener {

            }
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<SimplePokemon>() {
    override fun areItemsTheSame(
        oldItem: SimplePokemon,
        newItem: SimplePokemon
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: SimplePokemon,
        newItem: SimplePokemon
    ): Boolean {
        return oldItem == newItem
    }
}