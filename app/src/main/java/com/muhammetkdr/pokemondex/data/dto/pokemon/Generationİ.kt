package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİ(
    @SerializedName("red-blue")
    val redBlue: RedBlue?,
    @SerializedName("yellow")
    val yellow: Yellow?
)