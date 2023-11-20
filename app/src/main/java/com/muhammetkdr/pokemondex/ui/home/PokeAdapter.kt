package com.muhammetkdr.pokemondex.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.Result
import com.muhammetkdr.pokemondex.databinding.ItemPokemonBinding

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.PokemonViewHolder>() {

    private val items = mutableListOf<Result>()

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {

            binding.ivPokemon.setPokemonImage(adapterPosition.plus(1).toString())
            binding.number.text = adapterPosition.plus(1).toString()
            binding.pokemonName.text = item.name

            binding.root.setOnClickListener{
                item.name?.let { it1 -> onItemClickListener?.invoke(it1) }
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

    fun updatePokemonList(newList: List<Result>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}