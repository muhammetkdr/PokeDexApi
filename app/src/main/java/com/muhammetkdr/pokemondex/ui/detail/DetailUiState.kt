package com.muhammetkdr.pokemondex.ui.detail

data class PokemonUiState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val weight: String = "",
    val height: String = "",
    val moves: List<String> = emptyList(),
    val pokeTypes: List<String> = emptyList(),
    val hpStat: String = "",
    val hpStatProgress: Int = 0,
    val attackStat: String = "",
    val attackStatProgress: Int = 0,
    val defenseStat: String = "",
    val defenseStatProgress: Int = 0,
    val specialAttackStat: String = "",
    val specialAttackStatProgress: Int = 0,
    val specialDefenseStat: String = "",
    val specialDefenseStatProgress: Int = 0,
    val speedStat: String = "",
    val speedStatProgress: Int = 0,
    val pokemonColor: Int = 0
)

data class PokemonSpeciesUiState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val description: String = ""
)