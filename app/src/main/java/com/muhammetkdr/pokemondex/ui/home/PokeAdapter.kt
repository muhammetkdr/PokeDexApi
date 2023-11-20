package com.muhammetkdr.pokemondex.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.databinding.ItemPokemonBinding

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.PokemonViewHolder>() {

    private val items = mutableListOf<PokemonItem>()

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonItem) {

            binding.ivPokemon.setPokemonImage(item.imageUrl)
            binding.number.text = item.pokeId
            binding.pokemonName.text = item.pokeName

            binding.root.setOnClickListener{
               onItemClickListener?.invoke(item.pokeId,item.pokeName,item.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val bind = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(bind)
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

    private var onItemClickListener: ((String,String,String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String,String,String) -> Unit) {
        onItemClickListener = listener
    }
}