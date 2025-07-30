package com.parissakalaee.parkadecisionmaker.data.model

data class DecisionData(
    val decisionSubject: String = "",
    val alternatives: List<String> = listOf("", "", "", "", ""),
    val criteria: List<String> = listOf("", "", "", "", ""),
    val alternativePriorityMatrix: Array<DoubleArray> = Array(MATRIX_SIZE) { DoubleArray(MATRIX_SIZE) },
    val criteriaPriorityMatrix: Array<DoubleArray> = Array(MATRIX_SIZE) { DoubleArray(MATRIX_SIZE) }
) {
    companion object {
        const val MATRIX_SIZE = 5
    }
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DecisionData

        if (decisionSubject != other.decisionSubject) return false
        if (alternatives != other.alternatives) return false
        if (criteria != other.criteria) return false
        if (!alternativePriorityMatrix.contentDeepEquals(other.alternativePriorityMatrix)) return false
        if (!criteriaPriorityMatrix.contentDeepEquals(other.criteriaPriorityMatrix)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = decisionSubject.hashCode()
        result = 31 * result + alternatives.hashCode()
        result = 31 * result + criteria.hashCode()
        result = 31 * result + alternativePriorityMatrix.contentDeepHashCode()
        result = 31 * result + criteriaPriorityMatrix.contentDeepHashCode()
        return result
    }
}

data class DecisionResult(
    val alternativeScores: List<Double>,
    val bestAlternativeIndex: Int,
    val consistencyRatio: Double
)