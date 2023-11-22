package com.muhammetkdr.pokemondex.domain.model

data class PokemonEntity(
    val weight:String,
    val height: String ,
    val moves: List<String>,
    val pokeTypes: List<String>,
    val hpStat: String,
    val hpStatProgress: Int,
    val attackStat: String,
    val attackStatProgress: Int,
    val defenseStat: String,
    val defenseStatProgress: Int,
    val specialAttackStat: String,
    val specialAttackStatProgress: Int,
    val specialDefenseStat: String,
    val specialDefenseStatProgress: Int,
    val speedStat: String,
    val speedStatProgress: Int,
    val pokemonColor:Int
)