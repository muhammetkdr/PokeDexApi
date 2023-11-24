package com.muhammetkdr.pokemondex.di

import android.content.Context
import com.muhammetkdr.pokemondex.common.utils.indicator.DefaultIndicatorPresenter
import com.muhammetkdr.pokemondex.common.utils.indicator.IndicatorPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped


@Module
@InstallIn(FragmentComponent::class)
object AppModule {

    @Provides
    @FragmentScoped
    fun provideIndicatorPresenter(@ActivityContext context: Context): IndicatorPresenter =
        DefaultIndicatorPresenter(context)

}