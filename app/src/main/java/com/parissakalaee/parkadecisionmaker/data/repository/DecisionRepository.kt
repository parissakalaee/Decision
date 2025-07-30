package com.parissakalaee.parkadecisionmaker.data.repository

import android.content.SharedPreferences
import com.parissakalaee.parkadecisionmaker.data.model.DecisionData
import com.parissakalaee.parkadecisionmaker.data.model.DecisionResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class DecisionRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private val _decisionData = MutableStateFlow(DecisionData())
    val decisionData: StateFlow<DecisionData> = _decisionData.asStateFlow()
    
    private val _decisionResult = MutableStateFlow<DecisionResult?>(null)
    val decisionResult: StateFlow<DecisionResult?> = _decisionResult.asStateFlow()

    fun updateDecisionSubject(subject: String) {
        _decisionData.value = _decisionData.value.copy(decisionSubject = subject)
    }

    fun updateAlternatives(alternatives: List<String>) {
        _decisionData.value = _decisionData.value.copy(alternatives = alternatives)
    }

    fun updateCriteria(criteria: List<String>) {
        _decisionData.value = _decisionData.value.copy(criteria = criteria)
    }

    fun updateAlternativePriorityMatrix(matrix: Array<DoubleArray>) {
        _decisionData.value = _decisionData.value.copy(alternativePriorityMatrix = matrix)
    }

    fun updateCriteriaPriorityMatrix(matrix: Array<DoubleArray>) {
        _decisionData.value = _decisionData.value.copy(criteriaPriorityMatrix = matrix)
    }

    fun calculateDecisionResult(): DecisionResult {
        val data = _decisionData.value
        
        // Calculate alternative scores using AHP method
        val alternativeScores = calculateAlternativeScores(
            data.alternativePriorityMatrix,
            data.criteriaPriorityMatrix
        )
        
        val bestAlternativeIndex = alternativeScores.indices.maxByOrNull { alternativeScores[it] } ?: 0
        val consistencyRatio = calculateConsistencyRatio(data.criteriaPriorityMatrix)
        
        val result = DecisionResult(alternativeScores, bestAlternativeIndex, consistencyRatio)
        _decisionResult.value = result
        
        return result
    }

    private fun calculateAlternativeScores(
        alternativeMatrix: Array<DoubleArray>,
        criteriaMatrix: Array<DoubleArray>
    ): List<Double> {
        val criteriaWeights = calculateEigenvector(criteriaMatrix)
        val alternativeWeights = mutableListOf<List<Double>>()
        
        // Calculate weights for each alternative against each criterion
        for (i in 0 until DecisionData.MATRIX_SIZE) {
            val criterionMatrix = Array(DecisionData.MATRIX_SIZE) { DoubleArray(DecisionData.MATRIX_SIZE) }
            for (j in 0 until DecisionData.MATRIX_SIZE) {
                for (k in 0 until DecisionData.MATRIX_SIZE) {
                    criterionMatrix[j][k] = alternativeMatrix[j][k]
                }
            }
            alternativeWeights.add(calculateEigenvector(criterionMatrix))
        }
        
        // Calculate final scores
        val scores = mutableListOf<Double>()
        for (i in 0 until DecisionData.MATRIX_SIZE) {
            var score = 0.0
            for (j in 0 until DecisionData.MATRIX_SIZE) {
                if (j < alternativeWeights.size && i < alternativeWeights[j].size) {
                    score += criteriaWeights[j] * alternativeWeights[j][i]
                }
            }
            scores.add(score)
        }
        
        return scores
    }

    private fun calculateEigenvector(matrix: Array<DoubleArray>): List<Double> {
        val size = matrix.size
        val weights = DoubleArray(size)
        
        // Simple geometric mean method for eigenvector calculation
        for (i in 0 until size) {
            var product = 1.0
            for (j in 0 until size) {
                product *= if (matrix[i][j] != 0.0) matrix[i][j] else 1.0
            }
            weights[i] = product.pow(1.0 / size)
        }
        
        // Normalize
        val sum = weights.sum()
        return if (sum != 0.0) {
            weights.map { it / sum }
        } else {
            List(size) { 1.0 / size }
        }
    }

    private fun calculateConsistencyRatio(matrix: Array<DoubleArray>): Double {
        // Simplified consistency ratio calculation
        // In a full implementation, this would calculate the actual CR
        return 0.1 // Placeholder
    }

    fun clearDecisionData() {
        _decisionData.value = DecisionData()
        _decisionResult.value = null
    }

    fun saveToPreferences() {
        val data = _decisionData.value
        with(sharedPreferences.edit()) {
            putString("decision_subject", data.decisionSubject)
            for (i in data.alternatives.indices) {
                putString("alternative_$i", data.alternatives[i])
            }
            for (i in data.criteria.indices) {
                putString("criteria_$i", data.criteria[i])
            }
            apply()
        }
    }

    fun loadFromPreferences() {
        val subject = sharedPreferences.getString("decision_subject", "") ?: ""
        val alternatives = mutableListOf<String>()
        val criteria = mutableListOf<String>()
        
        for (i in 0 until DecisionData.MATRIX_SIZE) {
            alternatives.add(sharedPreferences.getString("alternative_$i", "") ?: "")
            criteria.add(sharedPreferences.getString("criteria_$i", "") ?: "")
        }
        
        _decisionData.value = DecisionData(
            decisionSubject = subject,
            alternatives = alternatives,
            criteria = criteria
        )
    }
}