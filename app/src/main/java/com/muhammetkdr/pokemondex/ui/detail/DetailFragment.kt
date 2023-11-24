package com.muhammetkdr.pokemondex.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.ui.base.BaseFragment
import com.muhammetkdr.pokemondex.common.gone
import com.muhammetkdr.pokemondex.common.inflate
import com.muhammetkdr.pokemondex.common.observeIfNotNull
import com.muhammetkdr.pokemondex.common.setColor
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.common.setProgressColor
import com.muhammetkdr.pokemondex.common.setSafeOnClickListener
import com.muhammetkdr.pokemondex.common.show
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
        getPokemonData()
    }

    private fun getPokemonData() {
        viewModel.getPokemon(args.name)
    }

    private fun observeUi() {
        viewModel.pokemonDetail.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    requireView().showSnackbar(it.errorMessage)
                }

                else -> {
                    handlePokemonDetailSuccess(it)
                }
            }
        }

        viewModel.pokemonStateSpecies.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    requireView().showSnackbar(it.errorMessage)
                }

                else -> {
                    with(binding){
                        root.visibility = View.VISIBLE
                        tvDescription.text = it.description
                    }
                }
            }
        }
    }

    private fun handlePokemonDetailSuccess(pokeUiState: PokemonUiState) {
        with(binding) {

            pokeName.text = pokeUiState.pokeName
            pokeId.text = pokeUiState.pokeId
            ivPokeImg.setPokemonImage(pokeUiState.pokeImgUrl)

            if(pokeUiState.isFirst){
                btnPrevious.gone()
            }else{
                btnPrevious.show()
                btnPrevious.setSafeOnClickListener {
                    viewModel.getPreviousItem(pokeUiState.pokeUuid)
                }
            }

            if (pokeUiState.isLast){
                btnNext.gone()
            }else{
                btnNext.show()
                btnNext.setSafeOnClickListener {
                    viewModel.getNextItem(pokeUiState.pokeUuid)
                }
            }

            tvWeight.text = pokeUiState.weight
            tvHeight.text = pokeUiState.height

            tvFirstMove.text = pokeUiState.moves.first()

            if (pokeUiState.moves.size > ONE) {
                tvSecondMove.text = pokeUiState.moves[SECOND_ITEM]
            }

            adapter.updatePokemonList(pokeUiState.pokeTypes)

            tvHPValue.text = pokeUiState.hpStat
            tvAttackValue.text = pokeUiState.attackStat
            tvDefValue.text = pokeUiState.defenseStat
            tvSpAttackValue.text = pokeUiState.specialAttackStat
            tvSpDefenseValue.text = pokeUiState.specialDefenseStat
            tvSpeedValue.text = pokeUiState.speedStat

            pbHP.setProgress(pokeUiState.hpStatProgress, true)
            pbAttack.setProgress(pokeUiState.attackStatProgress, true)
            pbDef.setProgress(pokeUiState.defenseStatProgress, true)
            pbSpAtk.setProgress(pokeUiState.specialAttackStatProgress, true)
            pbSpDef.setProgress(pokeUiState.specialDefenseStatProgress, true)
            pbSpeed.setProgress(pokeUiState.speedStatProgress, true)

            pbHP.setProgressColor(pokeUiState.pokemonColor)
            pbAttack.setProgressColor(pokeUiState.pokemonColor)
            pbDef.setProgressColor(pokeUiState.pokemonColor)
            pbSpAtk.setProgressColor(pokeUiState.pokemonColor)
            pbSpDef.setProgressColor(pokeUiState.pokemonColor)
            pbSpeed.setProgressColor(pokeUiState.pokemonColor)

            clDetail.setBackgroundColor(requireContext().getColor(pokeUiState.pokemonColor))

            tvAbout.setColor(pokeUiState.pokemonColor)
            tvBaseStats.setColor(pokeUiState.pokemonColor)

            tvHp.setColor(pokeUiState.pokemonColor)
            tvAtk.setColor(pokeUiState.pokemonColor)
            tvDef.setColor(pokeUiState.pokemonColor)
            tvSatk.setColor(pokeUiState.pokemonColor)
            tvSdef.setColor(pokeUiState.pokemonColor)
            tvSpd.setColor(pokeUiState.pokemonColor)
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