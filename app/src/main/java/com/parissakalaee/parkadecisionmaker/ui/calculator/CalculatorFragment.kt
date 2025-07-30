package com.parissakalaee.parkadecisionmaker.ui.calculator

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.parissakalaee.parkadecisionmaker.R
import com.parissakalaee.parkadecisionmaker.databinding.DialogGetSubjectBinding
import com.parissakalaee.parkadecisionmaker.databinding.FragmentCalculatorBinding
import com.parissakalaee.parkadecisionmaker.ui.calculator.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        
        setupUI()
        observeViewModel()
        
        return binding.root
    }
    
    private fun setupUI() {
        val clickableViews: List<View> = listOf(
            binding.buttonQ1, binding.buttonQ2, binding.buttonQ3,
            binding.buttonQ4, binding.buttonQ5, binding.buttonCompute, binding.buttonReset
        )
        
        for (item in clickableViews) {
            item.setOnClickListener { handleButtonClick(it) }
        }
        
        // Initial dice roll
        viewModel.rollDice()
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.diceValue.collect { diceValue ->
                        updateDiceImage(diceValue)
                    }
                }
                
                launch {
                    viewModel.calculationResult.collect { result ->
                        result?.let {
                            // Show result dialog
                            showResultDialog(it)
                        }
                    }
                }
                
                launch {
                    viewModel.isCalculating.collect { isCalculating ->
                        // Update UI to show loading state
                        binding.buttonCompute.isEnabled = !isCalculating
                    }
                }
            }
        }
    }
    
    private fun handleButtonClick(view: View) {
        viewModel.rollDice()
        
        when (view.id) {
            R.id.buttonQ1 -> showSubjectDialog()
            R.id.buttonQ2 -> view.findNavController().navigate(
                CalculatorFragmentDirections.actionCalculatorFragmentToGetAlternativesFragment()
            )
            R.id.buttonQ3 -> view.findNavController().navigate(
                CalculatorFragmentDirections.actionCalculatorFragmentToGetCriteriaFragment()
            )
            R.id.buttonQ4 -> view.findNavController().navigate(
                CalculatorFragmentDirections.actionCalculatorFragmentToPrioritizeAlternativesFragment()
            )
            R.id.buttonQ5 -> {
                // TODO: Navigate to prioritize criteria or show dialog
                // For now, just show a placeholder
            }
            R.id.buttonCompute -> viewModel.compute()
            R.id.buttonReset -> viewModel.reset()
            else -> viewModel.rollDice()
        }
    }
    
    private fun updateDiceImage(diceValue: Int) {
        val drawableResource = when (diceValue) {
            1 -> R.drawable.vector_dice_1
            2 -> R.drawable.vector_dice_2
            3 -> R.drawable.vector_dice_3
            4 -> R.drawable.vector_dice_4
            5 -> R.drawable.vector_dice_5
            else -> R.drawable.vector_dice_6
        }
        binding.imageViewDice.setImageResource(drawableResource)
    }
    
    private fun showSubjectDialog() {
        val dialog = Dialog(requireContext())
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = DialogGetSubjectBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(dialogBinding.root)
        
        // Pre-populate with existing subject from current data
        val currentData = viewModel.decisionData.value
        dialogBinding.edtGetSubject.setText(currentData.decisionSubject)
        
        dialogBinding.btnOkDialogGetSubject.setOnClickListener {
            val subject = dialogBinding.edtGetSubject.text?.toString() ?: ""
            viewModel.updateDecisionSubject(subject)
            dialog.dismiss()
        }
        
        dialogBinding.btnClearDialogGetSubject.setOnClickListener {
            dialogBinding.edtGetSubject.setText("")
        }
        
        dialog.show()
    }
    
    private fun showResultDialog(result: com.parissakalaee.parkadecisionmaker.data.model.DecisionResult) {
        // TODO: Implement result dialog with modern approach
        // For now, we'll use a simple approach
        val bestIndex = result.bestAlternativeIndex
        val bestScore = if (result.alternativeScores.isNotEmpty()) result.alternativeScores[bestIndex] else 0.0
        
        // Show a simple dialog or navigate to a result fragment
        // This is a placeholder - in a real implementation, you'd create a proper result dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}