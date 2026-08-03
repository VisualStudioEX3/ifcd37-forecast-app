package com.visualstudioex3.application.models

/**
 * Municipality data model.
 *
 * @param code Municipality code.
 * @param name Municipality name.
 * @param province Province name where the municipality belongs.
 * @param autnomousCommunity Autonomous Community name where the municipality belongs.
 */
data class MunicipalityData(
    val code: String,
    val name: String,
    val province: String,
    val autnomousCommunity: String
) {
}
