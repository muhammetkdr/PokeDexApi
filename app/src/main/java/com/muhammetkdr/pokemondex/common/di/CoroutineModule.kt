package com.muhammetkdr.pokemondex.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object CoroutineModule {

    @Dispatcher(DispatcherType.Io)
    @Provides
    @ViewModelScoped
    fun provideDispatcheIo(): CoroutineDispatcher = Dispatchers.IO

    @Dispatcher(DispatcherType.Unconfined)
    @Provides
    @ViewModelScoped
    fun provideDispatcherUncf(): CoroutineDispatcher = Dispatchers.Unconfined

    @Dispatcher(DispatcherType.Default)
    @Provides
    @ViewModelScoped
    fun provideDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @Dispatcher(DispatcherType.Main)
    @Provides
    @ViewModelScoped
    fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val type: DispatcherType)

enum class DispatcherType {
    Main,
    Io,
    Default,
    Unconfined
}
