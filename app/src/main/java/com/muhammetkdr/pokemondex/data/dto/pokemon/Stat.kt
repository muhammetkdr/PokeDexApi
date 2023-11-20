package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int?,
    @SerializedName("effort")
    val effort: Int?,
    @SerializedName("stat")
    val stat: StatX?
)