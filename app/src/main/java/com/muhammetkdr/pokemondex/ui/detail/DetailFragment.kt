package com.muhammetkdr.pokemondex.ui.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.base.BaseFragment
import com.muhammetkdr.pokemondex.common.inflate
import com.muhammetkdr.pokemondex.common.observeIfNotNull
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.databinding.FragmentDetailBinding
import com.muhammetkdr.pokemondex.ui.detail.adapter.SpeciesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val binding by inflate(FragmentDetailBinding::bind)
    override val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private val adapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUi()
        initRv()
        viewModel.getPokemon(args.name)
    }

    private fun initRv() {
        binding.rvTypes.adapter = adapter
    }

    private fun observeUi() {

        viewModel.pokemonDetail.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    binding.tvDescr.text = it.errorMessage
                }

                else -> {
                    with(binding) {

                        ivPokemon.setPokemonImage(args.imgUrl)

                        tvWeight.text = it.weight
                        tvHeight.text = it.height
                        tvMoves.text = it.moves.first() // TODO BURADA 2 TANE GELEBİLİR DÜZELT
                    }

                    adapter.updatePokemonList(it.pokeTypes)

                    with(binding.statsContainer) {

                        tvHPValue.text = it.hpStat
                        tvAttackValue.text = it.attackStat
                        tvDefValue.text = it.defenseStat
                        tvSpAttackValue.text = it.specialAttackStat
                        tvSpDefenseValue.text = it.specialDefenseStat
                        tvSpeedValue.text = it.speedStat

                        pbHP.setProgress(it.hpStatProgress, true)
                        pbAttack.setProgress(it.attackStatProgress, true)
                        pbDef.setProgress(it.defenseStatProgress, true)
                        pbSpAtk.setProgress(it.specialAttackStatProgress, true)
                        pbSpDef.setProgress(it.specialDefenseStatProgress, true)
                        pbSpeed.setProgress(it.speedStatProgress, true)

                        pbHP.progressTintList =
                            ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbAttack.progressTintList =
                            ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbDef.progressTintList =
                            ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbSpAtk.progressTintList =
                            ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbSpDef.progressTintList =
                            ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))
                        pbSpeed.progressTintList =
                            ColorStateList.valueOf(requireContext().getColor(it.pokemonColor))

                    }

                }
            }
        }

        viewModel.pokemonStateSpecies.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {

                }

                else -> {
                    binding.tvDescr.text = it.description
                }
            }
        }
    }

}