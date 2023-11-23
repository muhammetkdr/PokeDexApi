package com.muhammetkdr.pokemondex.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.base.BaseFragment
import com.muhammetkdr.pokemondex.common.inflate
import com.muhammetkdr.pokemondex.common.observeIfNotNull
import com.muhammetkdr.pokemondex.common.show
import com.muhammetkdr.pokemondex.common.showSnackbar
import com.muhammetkdr.pokemondex.databinding.FragmentHomeBinding
import com.muhammetkdr.pokemondex.ui.home.adapter.PokeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by inflate(FragmentHomeBinding::bind)
    override val viewModel by viewModels<HomeViewModel>()
    private val adapter: PokeAdapter by lazy { PokeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUi()
        initRvAdapter()
        handleSearchbar()
    }

    private fun handleSearchbar() {
        binding.searchTextField.addTextChangedListener(object : TextWatcher {
            var job: Job? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                job?.cancel()
            }

            override fun afterTextChanged(s: Editable?) {
                job = lifecycleScope.launch {
                    delay(700)
                    viewModel.filterPokemonQuery(s)
                }
            }
        })
    }

    private fun initRvAdapter() {
        binding.rvPoke.adapter = adapter

        adapter.setOnItemClickListener { id, name, url ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id, name, url)
            findNavController().navigate(action)
        }
    }

    private fun observeUi() {
        viewModel.pokemonsQuery.observeIfNotNull(viewLifecycleOwner) {
            adapter.updatePokemonList(it)
        }

        viewModel.pokeState.observeIfNotNull(viewLifecycleOwner) {
            when {
                it.isError -> {
                    handleError(it.errorMessage)
                }

                else -> {
                    handleSuccess(it.pokeItems)
                }
            }
        }
    }

    private fun handleSuccess(pokeItems: List<PokemonItem>) {
        adapter.updatePokemonList(pokeItems)
        viewModel.setPokemonListData(pokeItems)
        binding.clHome.show()
    }

    private fun handleError(errorMessage: String) {
        requireView().showSnackbar(errorMessage)
    }
}