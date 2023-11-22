package com.muhammetkdr.pokemondex.data.source

import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import com.muhammetkdr.pokemondex.data.dto.pokemonspecies.PokemonSpecies
import retrofit2.Response

interface PokeRemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonList>
    suspend fun getPokemonInfo(name: String): Response<Pokemon>
    suspend fun getPokemonSpecies(name: String): Response<PokemonSpecies>
}