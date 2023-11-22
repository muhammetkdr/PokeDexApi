package com.muhammetkdr.pokemondex.domain.repository

import com.muhammetkdr.pokemondex.common.networkresponse.NetworkResponse
import com.muhammetkdr.pokemondex.domain.model.PokemonEntity
import com.muhammetkdr.pokemondex.domain.model.PokemonListEntity
import com.muhammetkdr.pokemondex.domain.model.PokemonSpeciesEntity
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<NetworkResponse<List<PokemonListEntity>>>
/*    fun getPokemonInfo(name: String): Flow<NetworkResponse<PokemonEntity>>
    fun getPokemonSpecies(name: String): Flow<NetworkResponse<PokemonSpeciesEntity>>*/
}