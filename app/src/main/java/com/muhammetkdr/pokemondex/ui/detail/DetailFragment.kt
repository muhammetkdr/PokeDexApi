package com.muhammetkdr.pokemondex.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.pokemondex.common.NetworkResponse
import com.muhammetkdr.pokemondex.common.setPokemonImage
import com.muhammetkdr.pokemondex.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment @Inject constructor() : Fragment() {

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
        viewModel.getPokemon(args.id)
        observeUi()
    }

    private fun observeUi() {
        viewModel.pokemonState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> {

                }

                NetworkResponse.Loading -> {

                }

                is NetworkResponse.Success -> {
                    binding.ivPokemon.setPokemonImage(response.data.id.toString())
                    binding.tvPokemonName.text = response.data.name
                    binding.tvPokemonDetail.text = response.data.types?.get(0)?.type?.name
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}