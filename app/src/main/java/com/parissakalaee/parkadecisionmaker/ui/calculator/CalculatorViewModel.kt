package com.parissakalaee.parkadecisionmaker.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parissakalaee.parkadecisionmaker.data.model.DecisionResult
import com.parissakalaee.parkadecisionmaker.data.repository.DecisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val decisionRepository: DecisionRepository
) : ViewModel() {

    private val _diceValue = MutableStateFlow(1)
    val diceValue: StateFlow<Int> = _diceValue.asStateFlow()

    private val _isCalculating = MutableStateFlow(false)
    val isCalculating: StateFlow<Boolean> = _isCalculating.asStateFlow()

    private val _calculationResult = MutableStateFlow<DecisionResult?>(null)
    val calculationResult: StateFlow<DecisionResult?> = _calculationResult.asStateFlow()

    val decisionData = decisionRepository.decisionData

    init {
        decisionRepository.loadFromPreferences()
    }

    fun rollDice() {
        _diceValue.value = Random.nextInt(1, 7)
    }

    fun compute() {
        viewModelScope.launch {
            _isCalculating.value = true
            try {
                val result = decisionRepository.calculateDecisionResult()
                _calculationResult.value = result
            } catch (e: Exception) {
                // Handle calculation error
                _calculationResult.value = null
            } finally {
                _isCalculating.value = false
            }
        }
    }

    fun reset() {
        viewModelScope.launch {
            decisionRepository.clearDecisionData()
            _calculationResult.value = null
            _diceValue.value = 1
        }
    }

    fun updateDecisionSubject(subject: String) {
        decisionRepository.updateDecisionSubject(subject)
        decisionRepository.saveToPreferences()
    }

    fun navigateToGetAlternatives() {
        // Navigation logic will be handled in the fragment
    }

    fun navigateToGetCriteria() {
        // Navigation logic will be handled in the fragment
    }

    fun navigateToPrioritizeAlternatives() {
        // Navigation logic will be handled in the fragment
    }

    fun navigateToPrioritizeCriteria() {
        // Navigation logic will be handled in the fragment
    }

    fun navigateToSettings() {
        // Navigation logic will be handled in the fragment
    }
}