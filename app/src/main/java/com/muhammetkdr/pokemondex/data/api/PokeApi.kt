package com.muhammetkdr.pokemondex.data.api

import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Response<Pokemon>
}