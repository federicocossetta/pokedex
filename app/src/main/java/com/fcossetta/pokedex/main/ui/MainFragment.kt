package com.fcossetta.pokedex.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.fcossetta.pokedex.R
import com.fcossetta.pokedex.main.data.PokemonEvent
import com.fcossetta.pokedex.main.data.PokemonViewModel
import com.fcossetta.pokedex.main.data.model.SimplePokemon
import io.uniflow.androidx.flow.onEvents
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
    private lateinit var results: PagingData<SimplePokemon>
    val TAG = "TEST"

    private val viewModel: PokemonViewModel by sharedViewModel()
    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.context = requireContext()
        recycler_view.adapter = adapter
            onEvents(viewModel) {
                when (val event = it.peek()) {
                    is PokemonEvent.PokemonListFound -> {
                        lifecycleScope.launch {
                            event.pokemon.collect() {
                                adapter.submitData(it)
                            }
                        }
                    }
                }
            }
    }

}