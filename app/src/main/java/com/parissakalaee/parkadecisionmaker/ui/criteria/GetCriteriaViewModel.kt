package com.parissakalaee.parkadecisionmaker.ui.criteria

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
class GetCriteriaViewModel @Inject constructor(
    private val decisionRepository: DecisionRepository
) : ViewModel() {

    private val _criteria = MutableStateFlow(listOf("", "", "", "", ""))
    val criteria: StateFlow<List<String>> = _criteria.asStateFlow()

    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid.asStateFlow()

    init {
        viewModelScope.launch {
            decisionRepository.decisionData.collect { data ->
                _criteria.value = data.criteria
                validateCriteria()
            }
        }
    }

    fun updateCriterion(index: Int, value: String) {
        if (index in 0..4) {
            val updated = _criteria.value.toMutableList()
            updated[index] = value
            _criteria.value = updated
            decisionRepository.updateCriteria(updated)
            validateCriteria()
        }
    }

    fun clearCriteria() {
        val cleared = listOf("", "", "", "", "")
        _criteria.value = cleared
        decisionRepository.updateCriteria(cleared)
        validateCriteria()
    }

    private fun validateCriteria() {
        val nonEmptyCount = _criteria.value.count { it.trim().isNotEmpty() }
        _isValid.value = nonEmptyCount >= 2 // At least 2 criteria required
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