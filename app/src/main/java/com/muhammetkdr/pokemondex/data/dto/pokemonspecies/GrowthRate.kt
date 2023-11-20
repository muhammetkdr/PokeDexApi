package com.muhammetkdr.pokemondex.data.dto.pokemonspecies


import com.google.gson.annotations.SerializedName

data class GrowthRate(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)