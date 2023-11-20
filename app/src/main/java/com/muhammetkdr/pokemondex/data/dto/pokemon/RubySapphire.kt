package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class RubySapphire(
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?
)