package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Generationİii(
    @SerializedName("emerald")
    val emerald: Emerald?,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen?,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphire?
)