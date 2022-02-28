package com.parissakalaee.parkadecisionmaker.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.parissakalaee.parkadecisionmaker.R
import com.parissakalaee.parkadecisionmaker.ViewAlternativeComp
import com.parissakalaee.parkadecisionmaker.databinding.FragmentPrioritizeAlternativesBinding
import com.parissakalaee.parkadecisionmaker.fragment.CalculatorFragment.Companion.ARRAY_SIZE

class PrioritizeAlternativesFragment : Fragment() {
    private var _binding: FragmentPrioritizeAlternativesBinding? = null
    private val binding get() = _binding!!
    var viewAlternativeComp = arrayListOf<ViewAlternativeComp>()
    var parameterValue = arrayOf("", "", "", "", "")
    var alternativeValue = arrayOf("", "", "", "", "")
    var alternativePriorityValue = Array(ARRAY_SIZE) { DoubleArray(ARRAY_SIZE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrioritizeAlternativesBinding.inflate(inflater, container, false)

        viewAlternativeComp.clear()
        viewAlternativeComp.add(binding.viewAlternativeComp1)
        viewAlternativeComp.add(binding.viewAlternativeComp2)
        viewAlternativeComp.add(binding.viewAlternativeComp3)
        viewAlternativeComp.add(binding.viewAlternativeComp4)
        viewAlternativeComp.add(binding.viewAlternativeComp5)

        for (i in 0 until CalculatorFragment.ARRAY_SIZE) viewAlternativeComp[i].getID(i)
        for (i in 0 until CalculatorFragment.ARRAY_SIZE) viewAlternativeComp[i].setAltText(parameterValue[i], alternativeValue)
        var iCnt = 0
        for (i in 0 until CalculatorFragment.ARRAY_SIZE) {
            if (parameterValue[i].isEmpty()) {
                iCnt++
                viewAlternativeComp[i].visibility = View.GONE
            }
            if (iCnt == 5) {
                binding.textViewDialogPrioritizeAlternatives.text = getString(R.string.all_msg_complete_entries)
                binding.btnCancelDialogPrioritizeAlternatives.visibility = View.GONE
                binding.btnOkDialogPrioritizeAlternatives.text = getString(R.string.all_action_ok)
            }
            var jCnt = 0
            for (j in 0 until CalculatorFragment.ARRAY_SIZE) {
                if (alternativeValue[j].isEmpty()) jCnt++
                if (jCnt == 5) {
                    binding.textViewDialogPrioritizeAlternatives.text = getString(R.string.all_msg_complete_entries)
                    viewAlternativeComp[i].visibility = View.GONE
                    binding.btnCancelDialogPrioritizeAlternatives.visibility = View.GONE
                    binding.btnOkDialogPrioritizeAlternatives.text = getString(R.string.all_action_ok)
                }
            }
        }
        for (i in 0 until CalculatorFragment.ARRAY_SIZE) {
            for (j in 0 until CalculatorFragment.ARRAY_SIZE) {
                if (alternativeValue[j].isEmpty()) {
                    viewAlternativeComp[i].setVisibilityText(j)
                }
            }
        }
        binding.btnOkDialogPrioritizeAlternatives.setOnClickListener { view: View ->
            for (i in 0 until CalculatorFragment.ARRAY_SIZE) for (j in 0 until CalculatorFragment.ARRAY_SIZE) {
                alternativePriorityValue[i][j] = viewAlternativeComp[i].getMyAlternativeItem()[j]
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                prefsEditor.putInt(
                    ViewAlternativeComp.spinnerAlterSelection.get(i).get(j),
                    (5 - alternativePriorityValue[i][j]).toInt()
                )
            }
            view.findNavController().navigate(R.id.action_prioritizeAlternativesFragment_to_calculatorFragment)
        }
        binding.btnCancelDialogPrioritizeAlternatives.setOnClickListener {
            val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
            for (i in 0..4) for (j in 0..4) prefsEditor.remove(
                ViewAlternativeComp.spinnerAlterSelection[i][j]
            )
            for (i in 0 until CalculatorFragment.ARRAY_SIZE) viewAlternativeComp[i]
                .clearID()
            for (i in 0 until CalculatorFragment.ARRAY_SIZE) {
                for (j in 0 until CalculatorFragment.ARRAY_SIZE) {
                    alternativePriorityValue[i][j] = 0.0
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}