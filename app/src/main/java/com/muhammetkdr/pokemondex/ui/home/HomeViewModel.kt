package com.muhammetkdr.pokemondex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.pokemondex.common.NetworkResponse
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
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

    private val _pokeState = MutableLiveData<NetworkResponse<PokemonList>>()
    val pokeState: LiveData<NetworkResponse<PokemonList>> = _pokeState

    private fun getPokemonList() = viewModelScope.launch(Dispatchers.IO) {
        remoteDataSource.getPokemonList(150, 0).collect {
            _pokeState.postValue(it)
        }
    }

}