package com.muhammetkdr.pokemondex.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.muhammetkdr.pokemondex.R


fun ImageView.setPokemonImage(pokePath: String?) {
    Glide.with(this).load(pokePath).placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error).into(this)
}

fun getPokemonColorByType(pokemonType: String): Int = when (pokemonType) {
    "bug" -> R.color.background_type_bug

    "dark" -> R.color.background_type_dark

    "dragon" -> R.color.background_type_dragon

    "electric" -> R.color.background_type_electric

    "fairy" -> R.color.background_type_fairy

    "fighting" -> R.color.background_type_fighting

    "fire" -> R.color.background_type_fire

    "flying" -> R.color.background_type_flying

    "ghost" -> R.color.background_type_ghost

    "grass" -> R.color.background_type_grass

    "ground" -> R.color.background_type_ground

    "ice" -> R.color.background_type_ice

    "normal" -> R.color.background_type_normal

    "poison" -> R.color.background_type_poison

    "psychic" -> R.color.background_type_psychic

    "rock" -> R.color.background_type_rock

    "steel" -> R.color.background_type_steel

    "water" -> R.color.background_type_water

    else -> R.color.background_type_normal
}