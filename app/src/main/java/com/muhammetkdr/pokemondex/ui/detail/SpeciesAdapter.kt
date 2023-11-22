package com.muhammetkdr.pokemondex.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.pokemondex.common.inflateAdapterItem
import com.muhammetkdr.pokemondex.databinding.ItemPokemonTypeBinding

class SpeciesAdapter : RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {

    private val items = mutableListOf<String>()

    inner class SpeciesViewHolder(private val binding: ItemPokemonTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {

            binding.tvType.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        return SpeciesViewHolder(parent.inflateAdapterItem(ItemPokemonTypeBinding::inflate))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updatePokemonList(newList: List<String>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

}