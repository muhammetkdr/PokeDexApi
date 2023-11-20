package com.muhammetkdr.pokemondex.data.source

import com.muhammetkdr.pokemondex.common.NetworkResponse
import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokeRemoteDataSource {
    fun getPokemonList(limit:Int, offset:Int) : Flow<NetworkResponse<PokemonList>>
    fun getPokemonInfo(name:String): Flow<NetworkResponse<Pokemon>>
}