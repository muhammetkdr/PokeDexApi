package com.muhammetkdr.pokemondex.data.dto.pokemonlist


import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>?
)