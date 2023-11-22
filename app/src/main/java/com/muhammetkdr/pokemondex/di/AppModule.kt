package com.muhammetkdr.pokemondex.di

import android.content.Context
import com.muhammetkdr.pokemondex.utils.indicator.DefaultIndicatorPresenter
import com.muhammetkdr.pokemondex.utils.indicator.IndicatorPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    @ActivityScoped
    fun provideIndicatorPresenter(@ActivityContext context: Context): IndicatorPresenter = DefaultIndicatorPresenter(context)

}