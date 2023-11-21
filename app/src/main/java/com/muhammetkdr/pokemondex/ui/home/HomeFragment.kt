package com.muhammetkdr.pokemondex.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        binding.searchProgressbar.visibility = View.GONE
    }

    private fun initRvAdapter() {
        binding.rvPoke.adapter = adapter
        binding.rvPoke.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter.setOnItemClickListener { id, name, url ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id, name, url)
            findNavController().navigate(action)
        }

        binding.searchTextField.addTextChangedListener(object : TextWatcher {
            var job: Job? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

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

    private fun observeUi() {
        viewModel.pokemonsQuery.observe(viewLifecycleOwner) { state ->
            state?.let {
                adapter.updatePokemonList(it)
            }
        }

        viewModel.pokeState.observe(viewLifecycleOwner) { state ->
            state?.let {
                adapter.updatePokemonList(it)
                viewModel.setPokemonListData(state)
            }
        }
    }
}
