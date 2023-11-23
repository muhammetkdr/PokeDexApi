package com.muhammetkdr.pokemondex.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.base.BaseFragment
import com.muhammetkdr.pokemondex.common.inflate
import com.muhammetkdr.pokemondex.common.observeIfNotNull
import com.muhammetkdr.pokemondex.common.setColor
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.common.setProgressColor
import com.muhammetkdr.pokemondex.common.showSnackbar
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
        handleBackPress()
        viewModel.getPokemon(args.name)
    }

    private fun observeUi() {

        viewModel.pokemonDetail.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    requireView().showSnackbar(it.errorMessage)
                }

                else -> {
                    with(binding) {

                        pokeName.text = args.name
                        pokeId.text = args.id
                        ivPokeImg.setPokemonImage(args.imgUrl)

                        tvWeight.text = it.weight
                        tvHeight.text = it.height

                        tvFirstMove.text = it.moves.first()

                        if (it.moves.size > ONE) {
                            tvSecondMove.text = it.moves[SECOND_ITEM]
                        }

                        adapter.updatePokemonList(it.pokeTypes)

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

                        pbHP.setProgressColor(it.pokemonColor)
                        pbAttack.setProgressColor(it.pokemonColor)
                        pbDef.setProgressColor(it.pokemonColor)
                        pbSpAtk.setProgressColor(it.pokemonColor)
                        pbSpDef.setProgressColor(it.pokemonColor)
                        pbSpeed.setProgressColor(it.pokemonColor)

                        clDetail.setBackgroundColor(requireContext().getColor(it.pokemonColor))

                        tvAbout.setColor(it.pokemonColor)
                        tvBaseStats.setColor(it.pokemonColor)

                        tvHp.setColor(it.pokemonColor)
                        tvAtk.setColor(it.pokemonColor)
                        tvDef.setColor(it.pokemonColor)
                        tvSatk.setColor(it.pokemonColor)
                        tvSdef.setColor(it.pokemonColor)
                        tvSpd.setColor(it.pokemonColor)
                    }

                }
            }
        }

        viewModel.pokemonStateSpecies.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    requireView().showSnackbar(it.errorMessage)
                }

                else -> {
                    binding.root.visibility = View.VISIBLE
                    binding.tvDescription.text = it.description
                }
            }
        }
    }

    private fun handleBackPress() {
        binding.ivBackPress.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRv() {
        binding.rvPokeTypes.adapter = adapter
    }

    companion object {
        private const val SECOND_ITEM = 1
        private const val ONE = 1

    }

}