package com.muhammetkdr.pokemondex.data.repository

import com.muhammetkdr.pokemondex.common.mapTo
import com.muhammetkdr.pokemondex.common.networkresponse.NetworkResponse
import com.muhammetkdr.pokemondex.common.toPokemonList
import com.muhammetkdr.pokemondex.data.source.PokeRemoteDataSource
import com.muhammetkdr.pokemondex.domain.model.PokemonListEntity
import com.muhammetkdr.pokemondex.domain.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(private val remoteDataSource: PokeRemoteDataSource) : PokeRepository {
    override fun getPokemonList(limit: Int, offset: Int): Flow<NetworkResponse<List<PokemonListEntity>>> {
        return flow {
            emit(NetworkResponse.Loading)
            val response = remoteDataSource.getPokemonList(limit, offset)
            emit(NetworkResponse.Success(response.mapTo { it.toPokemonList() }))
        }.catch {
            NetworkResponse.Error(it.message.orEmpty())
        }
    }

    /*    override fun getPokemonInfo(name: String): Flow<NetworkResponse<PokemonEntity>> {

        }

        override fun getPokemonSpecies(name: String): Flow<NetworkResponse<PokemonSpeciesEntity>> {

        }*/
}