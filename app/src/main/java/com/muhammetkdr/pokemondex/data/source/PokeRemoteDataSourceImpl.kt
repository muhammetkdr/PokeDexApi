package com.muhammetkdr.pokemondex.data.source

import com.muhammetkdr.pokemondex.common.NetworkResponse
import com.muhammetkdr.pokemondex.data.api.PokeApi
import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import com.muhammetkdr.pokemondex.data.dto.pokemonspecies.PokemonSpecies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRemoteDataSourceImpl @Inject constructor(private val api: PokeApi) : PokeRemoteDataSource {
    override fun getPokemonList(limit:Int, offset:Int): Flow<NetworkResponse<PokemonList>> = flow {
        emit(NetworkResponse.Loading)
        val response = api.getPokemonList(limit = limit, offset = offset)
        if (response.isSuccessful){
            response.body()?.let {
                emit(NetworkResponse.Success(it))
            } ?: emit(NetworkResponse.Error(NETWORK_ERROR))
        }else{
            emit(NetworkResponse.Error(NETWORK_ERROR))
        }
    }.catch {
        emit(NetworkResponse.Error(it.message ?: NETWORK_ERROR))
    }

    override fun getPokemonInfo(name: String): Flow<NetworkResponse<Pokemon>> = flow {
        emit(NetworkResponse.Loading)
        val response = api.getPokemonInfo(name = name)
        if (response.isSuccessful){
            response.body()?.let {
                emit(NetworkResponse.Success(it))
            } ?: emit(NetworkResponse.Error(NETWORK_ERROR))
        }else{
            emit(NetworkResponse.Error(NETWORK_ERROR))
        }
    }.catch {
        emit(NetworkResponse.Error(it.message ?: NETWORK_ERROR))
    }

    override fun getPokemonSpecies(name: String): Flow<NetworkResponse<PokemonSpecies>> = flow {
        emit(NetworkResponse.Loading)
        val response = api.getPokemonSpecies(name = name)
        if (response.isSuccessful){
            response.body()?.let {
                emit(NetworkResponse.Success(it))
            } ?: emit(NetworkResponse.Error(NETWORK_ERROR))
        }else{
            emit(NetworkResponse.Error(NETWORK_ERROR))
        }
    }.catch {
        emit(NetworkResponse.Error(it.message ?: NETWORK_ERROR))
    }

    companion object{
        private const val NETWORK_ERROR = "NO DATA!"
    }
}