package com.muhammetkdr.pokemondex.data.di

import com.muhammetkdr.pokemondex.data.source.PokeRemoteDataSource
import com.muhammetkdr.pokemondex.data.source.PokeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteDataSource(pokeRemoteDataSourceImpl: PokeRemoteDataSourceImpl): PokeRemoteDataSource

}