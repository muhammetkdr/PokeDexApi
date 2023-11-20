package com.muhammetkdr.pokemondex.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.muhammetkdr.pokemondex.R


fun ImageView.setPokemonImage(pokePath:String?) {
    Glide.with(this).load(pokePath)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}