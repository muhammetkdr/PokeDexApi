package com.muhammetkdr.pokemondex.ui.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.common.inflate
import com.muhammetkdr.pokemondex.common.observeIfNotNull
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by inflate(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUi()

        viewModel.getPokemon(args.name)
    }

    private fun observeUi() {

        viewModel.pokemonDetail.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    binding.tvDescr.text = it.errorMessage
                }

                else -> {
                    with(binding){

                      ivPokemon.setPokemonImage(args.imgUrl)

                      tvWeight.text = it.weight
                      tvHeight.text = it.height
                      tvMoves.text = it.moves.first()
                    }


                    with(binding.statsContainer) {

                        tvHPValue.text = it.hpStat
                        tvAttackValue.text = it.attackStat
                        tvDefValue.text = it.defenseStat
                        tvSpAttackValue.text = it.specialAttackStat
                        tvSpDefenseValue.text = it.specialDefenseStat
                        tvSpeedValue.text = it.speedStat

                        pbHP.setProgress(it.hpStatProgress, true)
                        pbAttack.setProgress(it.attackStatProgress, true)
                        pbDef.setProgress(it.defenseStatProgress,true)
                        pbSpAtk.setProgress(it.specialAttackStatProgress,true)
                        pbSpDef.setProgress(it.specialDefenseStatProgress,true)
                        pbSpeed.setProgress(it.speedStatProgress,true)

                        pbHP.progressTintList = ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbAttack.progressTintList = ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbDef.progressTintList = ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbSpAtk.progressTintList = ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbSpDef.progressTintList = ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbSpeed.progressTintList = ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))

                    }

                }
            }
        }

        viewModel.pokemonStateSpecies.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError->{

                }
                else ->{
                    binding.tvDescr.text = it.description
                }
            }
        }
    }

}