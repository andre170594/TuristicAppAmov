package com.example.turisticappamov.mymodels

import com.example.turisticappamov.mymodels.ParOptionsAnswers
import java.io.Serializable

class Question(
    val pergunta: String? = null,
    val listOpt: ArrayList<ParOptionsAnswers>? = null,
    val explanation: String? = null,
    val numCorrect: Int = 0
) : Serializable {
    constructor() : this(null,  null, null, 0)
}