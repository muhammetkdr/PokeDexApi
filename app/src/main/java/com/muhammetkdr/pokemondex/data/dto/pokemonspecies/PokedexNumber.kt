package com.muhammetkdr.pokemondex.data.dto.pokemonspecies


import com.google.gson.annotations.SerializedName

data class PokedexNumber(
    @SerializedName("entry_number")
    val entryNumber: Int?,
    @SerializedName("pokedex")
    val pokedex: Pokedex?
)