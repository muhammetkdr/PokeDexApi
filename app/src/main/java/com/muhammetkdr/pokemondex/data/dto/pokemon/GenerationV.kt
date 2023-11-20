package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite?
)