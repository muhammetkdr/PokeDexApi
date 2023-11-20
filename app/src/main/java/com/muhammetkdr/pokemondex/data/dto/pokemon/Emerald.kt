package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Emerald(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?
)