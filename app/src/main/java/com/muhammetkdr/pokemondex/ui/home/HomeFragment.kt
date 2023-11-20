package com.muhammetkdr.pokemondex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.common.NetworkResponse
import com.muhammetkdr.pokemondex.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter: PokeAdapter by lazy { PokeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUi()
        initRvAdapter()
    }

    private fun initRvAdapter() {
        binding.rvPoke.adapter = adapter
        binding.rvPoke.layoutManager = GridLayoutManager(requireContext(),2)

        adapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun observeUi() {
        viewModel.pokeState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> {

                }

                NetworkResponse.Loading -> {

                }

                is NetworkResponse.Success -> {
                    response.data.results?.let {
                        adapter.updatePokemonList(it)
                    }
                }

            }
        }
    }

}