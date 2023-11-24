package com.muhammetkdr.pokemondex.ui.filterdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.muhammetkdr.pokemondex.databinding.FragmentFilterDialogBinding

class FilterDialogFragment(private val filter: (FilterType) -> Unit) : DialogFragment() {

    enum class FilterType {
        Number,
        Name,
    }

    private var _binding: FragmentFilterDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            filter.invoke(FilterType.Name)
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}