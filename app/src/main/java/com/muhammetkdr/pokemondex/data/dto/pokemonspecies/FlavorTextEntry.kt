package com.muhammetkdr.pokemondex.data.dto.pokemonspecies


import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String?,
    @SerializedName("language")
    val language: Language?,
    @SerializedName("version")
    val version: Version?
)