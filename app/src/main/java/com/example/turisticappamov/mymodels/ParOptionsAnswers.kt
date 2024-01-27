package com.example.turisticappamov.mymodels

import java.io.Serializable

class ParOptionsAnswers(
    val option: String? = null,
    val answers: Boolean? = null,
    var selected:Boolean? = false
) : Serializable {
    constructor() : this(null, null, selected = false)
}