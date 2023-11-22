package com.muhammetkdr.pokemondex.domain.usecase

import com.muhammetkdr.pokemondex.domain.repository.PokeRepository
import javax.inject.Inject

class GetPokemonSpeciesUseCase @Inject constructor(private val pokeRepository: PokeRepository) {
    operator fun invoke(name:String) = pokeRepository.getPokemonSpecies(name)
}