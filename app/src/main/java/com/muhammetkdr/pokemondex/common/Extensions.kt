package com.muhammetkdr.pokemondex.common

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.data.dto.pokemon.Pokemon
import com.muhammetkdr.pokemondex.data.dto.pokemonlist.PokemonList
import com.muhammetkdr.pokemondex.data.dto.pokemonspecies.PokemonSpecies
import com.muhammetkdr.pokemondex.domain.model.PokemonEntity
import com.muhammetkdr.pokemondex.domain.model.PokemonListEntity
import com.muhammetkdr.pokemondex.domain.model.PokemonSpeciesEntity
import retrofit2.Response

fun ImageView.setPokemonImage(pokePath: String?) {
    Glide.with(this).load(pokePath)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}

fun getPokemonColorByType(pokemonType: String): Int = when (pokemonType) {
    "bug" -> R.color.pokemon_type_bug

    "dark" -> R.color.pokemon_type_dark

    "dragon" -> R.color.pokemon_type_dragon

    "electric" -> R.color.pokemon_type_electric

    "fairy" -> R.color.pokemon_type_fairy

    "fighting" -> R.color.pokemon_type_fighting

    "fire" -> R.color.pokemon_type_fire

    "flying" -> R.color.pokemon_type_flying

    "ghost" -> R.color.pokemon_type_ghost

    "grass" -> R.color.pokemon_type_grass

    "ground" -> R.color.pokemon_type_ground

    "ice" -> R.color.pokemon_type_ice

    "normal" -> R.color.pokemon_type_normal

    "poison" -> R.color.pokemon_type_poison

    "psychic" -> R.color.pokemon_type_psychic

    "rock" -> R.color.pokemon_type_rock

    "steel" -> R.color.pokemon_type_steel

    "water" -> R.color.pokemon_type_water

    else -> R.color.pokemon_type_normal
}

inline fun <I, O> I.mapTo(crossinline mapper: (I) -> O): O {
    return mapper(this)
}

fun Response<PokemonList>.toPokemonListEntity(): List<PokemonListEntity> {
    return body()!!.results!!.map {
        PokemonListEntity(
            it.name.orEmpty()
        )
    }
}

fun Response<Pokemon>.toPokemonEntity(): PokemonEntity {
    return body()!!.run {
        PokemonEntity(
            weight = weight.toString(),
            height = height.toString(),
            moves = abilities!!.map { it.ability!!.name.orEmpty() },
            pokeTypes = types!!.map {
                it.type!!.name!!
            },
            hpStat =  stats!!.first { it.stat!!.name == "hp" }.baseStat.toString(),
            hpStatProgress = stats.first { it.stat!!.name == "hp" }.baseStat ?: 0,
            attackStat = stats.first { it.stat!!.name == "attack" }.baseStat.toString(),
            attackStatProgress = stats.first { it.stat!!.name == "attack" }.baseStat ?: 0,
            defenseStat = stats.first { it.stat!!.name == "defense" }.baseStat.toString(),
            defenseStatProgress = stats.first { it.stat!!.name == "defense" }.baseStat ?: 0,
            specialAttackStat = stats.first { it.stat!!.name == "special-attack" }.baseStat.toString(),
            specialAttackStatProgress = stats.first { it.stat!!.name == "special-attack" }.baseStat ?: 0,
            specialDefenseStat = stats.first { it.stat!!.name == "special-defense" }.baseStat.toString(),
            specialDefenseStatProgress = stats.first { it.stat!!.name == "special-defense" }.baseStat ?: 0,
            speedStat = stats.first { it.stat!!.name == "speed" }.baseStat.toString(),
            speedStatProgress = stats.first { it.stat!!.name == "speed" }.baseStat ?: 0,
            pokemonColor = getPokemonColorByType( types.first().type!!.name!!)
        )
    }
}

fun Response<PokemonSpecies>.toPokemonSpeciesEntity(): PokemonSpeciesEntity{
    return body()!!.run {
        PokemonSpeciesEntity(
            description = flavorTextEntries!![9].flavorText.orEmpty()
        )
    }
}

inline fun <T> LiveData<T>.observeIfNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner) { data ->
        if (data != null) {
            observer(data)
        }
    }
}