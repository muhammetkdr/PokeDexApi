package com.muhammetkdr.pokemondex.ui.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.pokemondex.common.networkresponse.NetworkResponse
import com.muhammetkdr.pokemondex.common.getPokemonColorByType
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemon(args.name)
        observeUi()
    }

    private fun observeUi() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> {

                }

                NetworkResponse.Loading -> {

                }

                is NetworkResponse.Success -> {
                    binding.ivPokemon.setPokemonImage(args.imgUrl)
                    var text = ""

                    response.data.types?.forEach { text += it.type?.name + " " }  // TODO TYPELARI BURADA RV icine bascaz

                    binding.tvDescr.text = text

                    val type = response.data.types?.first()?.type?.name ?: ""
                    val color = getPokemonColorByType(type)
//                    binding.statsContainer.statsConstraintL.setBackgroundColor(requireContext().getColor(color))

                    binding.tvWeight.text = response.data.weight.toString()
                    binding.tvHeight.text = response.data.height.toString()
                    binding.tvMoves.text = response.data.moves?.first()?.move?.name.toString()

                    with(binding.statsContainer) {

                        tvHPValue.text = response.data.stats?.first { it.stat?.name == "hp" }?.baseStat.toString()
                        tvAttackValue.text = response.data.stats?.first { it.stat?.name == "attack" }?.baseStat.toString()
                        tvDefValue.text = response.data.stats?.first { it.stat?.name == "defense" }?.baseStat.toString()
                        tvSpAttackValue.text = response.data.stats?.first { it.stat?.name == "special-attack" }?.baseStat.toString()
                        tvSpDefenseValue.text = response.data.stats?.first { it.stat?.name == "special-defense" }?.baseStat.toString()
                        tvSpeedValue.text = response.data.stats?.first { it.stat?.name == "speed" }?.baseStat.toString()

                        pbHP.setProgress(response.data.stats?.first { it.stat?.name == "hp" }?.baseStat!!, true)
                        pbAttack.setProgress(response.data.stats?.first { it.stat?.name == "attack" }?.baseStat!!, true)
                        pbDef.setProgress(response.data.stats?.first { it.stat?.name == "defense" }?.baseStat!!, true)
                        pbSpAtk.setProgress(response.data.stats?.first { it.stat?.name == "special-attack" }?.baseStat!!, true)
                        pbSpDef.setProgress(response.data.stats?.first { it.stat?.name == "special-defense" }?.baseStat!!, true)
                        pbSpeed.setProgress(response.data.stats?.first { it.stat?.name == "speed" }?.baseStat!!, true)

                        pbHP.progressTintList = ColorStateList.valueOf(requireContext().getColor(color))
                        pbAttack.progressTintList = ColorStateList.valueOf(requireContext().getColor(color))
                        pbDef.progressTintList = ColorStateList.valueOf(requireContext().getColor(color))
                        pbSpAtk.progressTintList = ColorStateList.valueOf(requireContext().getColor(color))
                        pbSpDef.progressTintList = ColorStateList.valueOf(requireContext().getColor(color))
                        pbSpeed.progressTintList = ColorStateList.valueOf(requireContext().getColor(color))
                    }
                }
            }
        }

        viewModel.pokemonStateSpecies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> {

                }

                NetworkResponse.Loading -> {

                }

                is NetworkResponse.Success -> {
                    // TODO BURASI description olacak!!
//                    binding.tvDescr.text = response.data.flavorTextEntries?.get(9)?.flavorText
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}