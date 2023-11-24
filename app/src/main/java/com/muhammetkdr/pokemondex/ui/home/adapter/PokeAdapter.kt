package com.muhammetkdr.pokemondex.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.pokemondex.common.inflateAdapterItem
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.databinding.ItemPokemonsBinding
import com.muhammetkdr.pokemondex.ui.home.PokemonItem

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.PokemonViewHolder>() {

    private val items = mutableListOf<PokemonItem>()

    inner class PokemonViewHolder(private val binding: ItemPokemonsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonItem) {

            binding.ivPokemon.setPokemonImage(item.imageUrl)
            binding.tvPokemonNumber.text = item.pokeId
            binding.tvPokemonName.text = item.pokeName

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item.pokeName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(parent.inflateAdapterItem(ItemPokemonsBinding::inflate))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updatePokemonList(newList: List<PokemonItem>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}