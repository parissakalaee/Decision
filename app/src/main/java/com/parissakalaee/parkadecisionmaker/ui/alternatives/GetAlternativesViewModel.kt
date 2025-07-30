package com.parissakalaee.parkadecisionmaker.ui.alternatives

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parissakalaee.parkadecisionmaker.data.repository.DecisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAlternativesViewModel @Inject constructor(
    private val decisionRepository: DecisionRepository
) : ViewModel() {

    private val _alternatives = MutableStateFlow(listOf("", "", "", "", ""))
    val alternatives: StateFlow<List<String>> = _alternatives.asStateFlow()

    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid.asStateFlow()

    init {
        viewModelScope.launch {
            decisionRepository.decisionData.collect { data ->
                _alternatives.value = data.alternatives
                validateAlternatives()
            }
        }
    }

    fun updateAlternative(index: Int, value: String) {
        if (index in 0..4) {
            val updated = _alternatives.value.toMutableList()
            updated[index] = value
            _alternatives.value = updated
            decisionRepository.updateAlternatives(updated)
            validateAlternatives()
        }
    }

    fun clearAlternatives() {
        val cleared = listOf("", "", "", "", "")
        _alternatives.value = cleared
        decisionRepository.updateAlternatives(cleared)
        validateAlternatives()
    }

    private fun validateAlternatives() {
        val nonEmptyCount = _alternatives.value.count { it.trim().isNotEmpty() }
        _isValid.value = nonEmptyCount >= 2 // At least 2 alternatives required
    }

    fun saveAndProceed(): Boolean {
        return if (_isValid.value) {
            decisionRepository.saveToPreferences()
            true
        } else {
            false
        }
    }
}