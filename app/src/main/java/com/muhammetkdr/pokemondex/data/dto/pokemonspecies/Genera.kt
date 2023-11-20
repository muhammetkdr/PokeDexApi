package com.muhammetkdr.pokemondex.data.dto.pokemonspecies


import com.google.gson.annotations.SerializedName

data class Genera(
    @SerializedName("genus")
    val genus: String?,
    @SerializedName("language")
    val language: Language?
)