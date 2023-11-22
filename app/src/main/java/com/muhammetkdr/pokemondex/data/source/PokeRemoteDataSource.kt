package com.muhammetkdr.pokemondex.data.source

import com.muhammetkdr.pokemondex.common.networkresponse.NetworkResponse
import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import com.muhammetkdr.pokemondex.data.dto.pokemonspecies.PokemonSpecies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PokeRemoteDataSource {
    suspend fun getPokemonList(limit:Int, offset:Int) : Response<PokemonList>
    fun getPokemonInfo(name:String): Flow<NetworkResponse<Pokemon>>
    fun getPokemonSpecies(name:String): Flow<NetworkResponse<PokemonSpecies>>
}