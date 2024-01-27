package com.example.turisticappamov.mymodels

import java.io.Serializable

class User (
    val username: String? = null,
    val password: String? = null,
    var lastScore: Double? = null,
    var avgScores: ArrayList<Double>? = ArrayList()  // Initialize avgScores
): Serializable {
    constructor(): this(null, null, 0.0, ArrayList())
    fun getAvgScoresList(): ArrayList<Double>? {
        return avgScores
    }

}
