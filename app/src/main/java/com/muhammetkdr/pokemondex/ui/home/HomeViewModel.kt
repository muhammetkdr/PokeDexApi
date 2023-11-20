package com.muhammetkdr.pokemondex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.pokemondex.common.NetworkResponse
import com.muhammetkdr.pokemondex.data.source.PokeRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val remoteDataSource: PokeRemoteDataSource) :
    ViewModel() {

    init {
        getPokemonList()
    }

    private val _pokeState = MutableLiveData<List<PokemonItem>>()
    val pokeState: LiveData<List<PokemonItem>> = _pokeState

    private fun getPokemonList() = viewModelScope.launch(Dispatchers.IO) {
        remoteDataSource.getPokemonList(150, 0).collect { response ->
            when (response) {
                is NetworkResponse.Error -> {

                }

                NetworkResponse.Loading -> {

                }

                is NetworkResponse.Success -> {
                    val uiItems = mutableListOf<PokemonItem>()
                    val uiState = response.data.results?.mapIndexed { index, data ->
                        PokemonItem(
                            pokeName = data.name!!,
                            pokeId = getPokemonId(index + 1),
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${index + 1}.png"
                        )
                    }

                    uiState?.forEach{
                        uiItems.add(it)
                    }
                    _pokeState.postValue(uiItems)
                }
            }
        }
    }

    private fun getPokemonId(pokemonId: Int): String {
        return "#${pokemonId.toString().padStart(3, '0')}"
    }
}

data class PokemonItem(
    val pokeName: String = "",
    val pokeId: String = "",
    val imageUrl: String = ""
)