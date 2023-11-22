package com.muhammetkdr.pokemondex.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.pokemondex.common.getPokemonColorByType
import com.muhammetkdr.pokemondex.common.inflateAdapterItem
import com.muhammetkdr.pokemondex.databinding.ItemPokemonTypeBinding

class MovesAdapter : RecyclerView.Adapter<MovesAdapter.MovesViewHolder>() {

    private val items = mutableListOf<String>()

    inner class MovesViewHolder(private val binding: ItemPokemonTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {

            with(binding) {
                tvType.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesViewHolder {
        return MovesViewHolder(parent.inflateAdapterItem(ItemPokemonTypeBinding::inflate))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updatePokemonList(newList: List<String>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

}