package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Yellow(
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_gray")
    val backGray: String?,
    @SerializedName("back_transparent")
    val backTransparent: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_gray")
    val frontGray: String?,
    @SerializedName("front_transparent")
    val frontTransparent: String?
)