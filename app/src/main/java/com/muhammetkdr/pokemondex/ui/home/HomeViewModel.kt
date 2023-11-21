package com.muhammetkdr.pokemondex.ui.home

import android.text.Editable
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

    private val _pokeUiState: MutableLiveData<HomeScreenUiState> =
        MutableLiveData(HomeScreenUiState.initial())
    val pokeState: LiveData<HomeScreenUiState> = _pokeUiState

    private val _pokemons: MutableLiveData<List<PokemonItem>> = MutableLiveData(emptyList())

    private val _pokemonsQuery = MutableLiveData<List<PokemonItem>>()
    val pokemonsQuery: LiveData<List<PokemonItem>> = _pokemonsQuery

    private fun getPokemonList() = viewModelScope.launch(Dispatchers.IO) {
        remoteDataSource.getPokemonList(150, 0).collect { response ->
            when (response) {
                is NetworkResponse.Error -> {
                    _pokeUiState.postValue(
                        HomeScreenUiState(
                            isError = true,
                            errorMessage = response.error
                        )
                    )
                }

                NetworkResponse.Loading -> {
                    _pokeUiState.postValue(HomeScreenUiState(isLoading = true))
                }

                is NetworkResponse.Success -> {
                    val pokemons = mutableListOf<PokemonItem>()
                    val pokeUiItem = response.data.results?.mapIndexed { index, data ->
                        PokemonItem(
                            pokeName = data.name!!,
                            pokeId = (index + 1).getPokemonId(),
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${index + 1}.png"
                        )
                    }

                    pokeUiItem?.forEach {
                        pokemons.add(it)
                    }
                    _pokeUiState.postValue(HomeScreenUiState(pokemons))
                }
            }
        }
    }

    fun setPokemonListData(pokemons: List<PokemonItem>) = viewModelScope.launch {
        _pokemons.postValue(pokemons)
    }

    fun filterPokemonQuery(query: Editable?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isNullOrBlank() || query.isEmpty()) {
                _pokemonsQuery.postValue(_pokemons.value)
                return@launch
            }

            val queryList = mutableListOf<PokemonItem>()

            _pokemons.value?.forEach {
                if (it.pokeName.contains(
                        query.toString(),
                        true
                    ) || it.pokeId.contains(query.toString())
                ) {
                    queryList.add(it)
                }
            }

            _pokemonsQuery.postValue(queryList)
        }
    }

    private fun Int.getPokemonId(): String {
        return "#${this.toString().padStart(3, '0')}"
    }

}

data class HomeScreenUiState(
    val pokeItems: List<PokemonItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    companion object {
        fun initial() = HomeScreenUiState(isLoading = true)
    }
}

data class PokemonItem(
    val pokeName: String = "",
    val pokeId: String = "",
    val imageUrl: String = ""
)