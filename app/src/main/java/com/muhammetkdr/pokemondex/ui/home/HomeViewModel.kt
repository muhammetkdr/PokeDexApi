package com.muhammetkdr.pokemondex.ui.home

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.pokemondex.base.BaseViewModel
import com.muhammetkdr.pokemondex.common.capitalizeWords
import com.muhammetkdr.pokemondex.common.networkresponse.NetworkResponse
import com.muhammetkdr.pokemondex.domain.repository.PokeRepository
import com.muhammetkdr.pokemondex.ui.filterdialog.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : BaseViewModel() {

    init {
        getPokemonList()
    }

    private val _pokeUiState: MutableLiveData<HomeScreenUiState> =
        MutableLiveData(HomeScreenUiState.initial())
    val pokeState: LiveData<HomeScreenUiState> = _pokeUiState

    private val _pokemons: MutableList<PokemonItem> = mutableListOf()

    private val _pokemonsQuery = MutableLiveData<List<PokemonItem>>()
    val pokemonsQuery: LiveData<List<PokemonItem>> = _pokemonsQuery

    var lastSelected = FilterType.Number

    private fun getPokemonList() = viewModelScope.launch {
        pokeRepository.getPokemonList(limit = limit, offset = offset)
            .onStart { showIndicator() }
            .onCompletion { hideIndicator() }
            .collect { response ->
                when (response) {
                    is NetworkResponse.Error -> {
                        _pokeUiState.postValue(
                            HomeScreenUiState(
                                isError = true,
                                errorMessage = response.error
                            )
                        )
                    }

                    NetworkResponse.Loading -> Unit // loading state has been handled by base structure

                    is NetworkResponse.Success -> {
                        val pokemons = mutableListOf<PokemonItem>()
                        val pokeUiItem = response.data.mapIndexed { index, data ->
                            PokemonItem(
                                pokeUuid = index + 1,
                                pokeName = data.pokeName.capitalizeWords(),
                                pokeId = (index + 1).getPokemonId(),
                                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${index + 1}.png",
                            )
                        }

                        pokeUiItem.forEach {
                            pokemons.add(it)
                        }
                        _pokeUiState.postValue(HomeScreenUiState(pokemons))
                    }
                }
            }
    }

    fun setPokemonListData(pokemons: List<PokemonItem>) = viewModelScope.launch {
        _pokemons.clear()
        _pokemons.addAll(pokemons)
    }

    fun filterPokemonQuery(query: Editable?) {
        viewModelScope.launch {
            if (query.isNullOrBlank() || query.isEmpty()) {
                _pokemonsQuery.postValue(_pokemons)
                return@launch
            }

            val queryList = mutableListOf<PokemonItem>()

            _pokemons.forEach {
                if (it.pokeName.contains(query.toString(), true) || it.pokeId.contains(query.toString())) {
                    queryList.add(it)
                }
            }

            _pokemonsQuery.postValue(queryList)
        }
    }

    fun sortListBySelectedItem(filterType: FilterType) = viewModelScope.launch {
        when (filterType) {
            FilterType.Name -> {
                val sorted = _pokemons.sortedBy {
                    it.pokeName
                }

                _pokemons.clear()
                _pokemons.addAll(sorted)

                _pokemonsQuery.postValue(_pokemons)

                lastSelected = FilterType.Name
            }

            FilterType.Number -> {
                val sorted = _pokemons.sortedBy {
                    it.pokeUuid
                }

                _pokemons.clear()
                _pokemons.addAll(sorted)

                _pokemonsQuery.postValue(_pokemons)

                lastSelected = FilterType.Number
            }
        }
    }

    private fun Int.getPokemonId(): String {
        return "#${this.toString().padStart(3, '0')}"
    }

    companion object {
        private const val limit = 150
        private const val offset = 0
    }
}