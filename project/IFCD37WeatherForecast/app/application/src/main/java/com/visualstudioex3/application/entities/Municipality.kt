package com.visualstudioex3.application.entities

/**
 * Municipality entity model.
 *
 * @param code Municipality code.
 * @param name Municipality name.
 * @param province Province name where the municipality belongs.
 * @param autonomousCommunity Autonomous Community name where the municipality belongs.
 */
data class Municipality(
    val code: String,
    val name: String,
    val province: String,
    val autonomousCommunity: String
)
