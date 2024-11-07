package com.example.turisticappamov.mymodels

import java.io.Serializable

class User (
    val userID: String? = null,
    val username: String? = null,
    val password: String? = null,
    var lastScore: Double? = null,
    var avgScores: ArrayList<Double>? = ArrayList(),
    var goDark: Boolean? = null,
    var goFeed: Boolean? = null
): Serializable {
    constructor(): this(null,null, null, 0.0, ArrayList(),null,null)
    fun getAvgScoresList(): ArrayList<Double>? {
        return avgScores
    }

}
