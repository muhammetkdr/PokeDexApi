package com.muhammetkdr.pokemondex.ui.filterdialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.muhammetkdr.pokemondex.R
import com.muhammetkdr.pokemondex.databinding.FragmentFilterDialogBinding

enum class FilterType {
    Number,
    Name,
}

class FilterDialogFragment(private val lastSelectedFilter: FilterType = FilterType.Number, private val filter: (FilterType) -> Unit) : DialogFragment() {
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

        with(binding){
            root.setOnClickListener {
                dismiss()
            }

            // Son seçilen checkbox'ı işaretle
            when (lastSelectedFilter) {
                FilterType.Name -> {
                    rbName.isChecked = true
                }
                FilterType.Number -> {
                    rbNumber.isChecked = true
                }
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rb_Name -> {
                        filter.invoke(FilterType.Name)
                        dismiss()
                    }

                    R.id.rb_number -> {
                        filter.invoke(FilterType.Number)
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}