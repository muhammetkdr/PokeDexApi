package com.muhammetkdr.pokemondex.data.dto.pokemon


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: MoveX?,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>?
)