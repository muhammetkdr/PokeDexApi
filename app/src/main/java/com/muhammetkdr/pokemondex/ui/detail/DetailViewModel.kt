package com.muhammetkdr.pokemondex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.pokemondex.common.networkresponse.NetworkResponse
import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonspecies.PokemonSpecies
import com.muhammetkdr.pokemondex.data.source.PokeRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val remoteDataSource: PokeRemoteDataSource) :
    ViewModel() {

    private val _pokemonDetail = MutableLiveData<NetworkResponse<Pokemon>>()
    val pokemonDetail: LiveData<NetworkResponse<Pokemon>> = _pokemonDetail

    private val _pokemonSpecies = MutableLiveData<NetworkResponse<PokemonSpecies>>()
    val pokemonStateSpecies: LiveData<NetworkResponse<PokemonSpecies>> = _pokemonSpecies

    fun getPokemon(name: String) = viewModelScope.launch(Dispatchers.IO) {
        remoteDataSource.getPokemonInfo(name = name).collect { PokemonInfoResponse->
            _pokemonDetail.postValue(PokemonInfoResponse)
            when(PokemonInfoResponse){
                is NetworkResponse.Error ->{

                }
                NetworkResponse.Loading -> {

                }
                is NetworkResponse.Success -> {

                }
            }
        }

        remoteDataSource.getPokemonSpecies(name = name).collect { PokemonSpeciesResponse->
            _pokemonSpecies.postValue(PokemonSpeciesResponse)
            when(PokemonSpeciesResponse){
                is NetworkResponse.Error -> {

                }
                NetworkResponse.Loading -> {

                }
                is NetworkResponse.Success -> {

                }
            }
        }
    }

}

data class PokemonUiState(
    val id: String = "",
    val pokeName: String = "",
    val pokeImgUrl : String = "",
    val pokeTypes: List<String> = emptyList(),
    val pokeStat: String = "",
    val pokeStatProgress: Int = 0,
    val pokemonColor:Int = 0,
)

data class PokemonSpeciesUiState(
    val description:String = ""
)

