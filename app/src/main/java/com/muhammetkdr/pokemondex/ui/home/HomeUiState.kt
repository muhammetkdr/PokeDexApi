package com.muhammetkdr.pokemondex.ui.home


data class HomeScreenUiState(
    val pokeItems: List<PokemonItem> = emptyList(),
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    companion object {
        fun initial() = HomeScreenUiState()
    }
}

data class PokemonItem(
    val pokeName: String = "",
    val pokeId: String = "",
    val imageUrl: String = ""
)