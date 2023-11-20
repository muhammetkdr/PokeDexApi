package com.muhammetkdr.pokemondex.data.dto.pokemonspecies


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("language")
    val language: Language?,
    @SerializedName("name")
    val name: String?
)