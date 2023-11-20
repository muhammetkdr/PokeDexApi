package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Gameİndice(
    @SerializedName("game_index")
    val gameİndex: Int?,
    @SerializedName("version")
    val version: Version?
)