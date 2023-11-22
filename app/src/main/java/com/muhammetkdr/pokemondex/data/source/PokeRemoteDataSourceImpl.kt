package com.muhammetkdr.pokemondex.data.source

import com.muhammetkdr.pokemondex.data.api.PokeApi
import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import com.muhammetkdr.pokemondex.data.dto.pokemonspecies.PokemonSpecies
import retrofit2.Response
import javax.inject.Inject

class PokeRemoteDataSourceImpl @Inject constructor(private val api: PokeApi) : PokeRemoteDataSource {
    override suspend fun getPokemonList(limit:Int, offset:Int): Response<PokemonList> {
        return api.getPokemonList(limit,offset)
    }

    override suspend fun getPokemonInfo(name: String): Response<Pokemon> {
        return api.getPokemonInfo(name = name)
    }

    override suspend fun getPokemonSpecies(name: String): Response<PokemonSpecies>  {
        return api.getPokemonSpecies(name = name)
    }
}