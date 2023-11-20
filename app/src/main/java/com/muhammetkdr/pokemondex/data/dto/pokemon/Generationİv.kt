package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİv(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearl?,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver?,
    @SerializedName("platinum")
    val platinum: Platinum?
)