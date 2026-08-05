package com.visualstudioex3.application.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Municipality entity model.
 *
 * @param code Municipality code.
 * @param name Municipality name.
 * @param province Province name where the municipality belongs.
 * @param autonomousCommunity Autonomous Community name where the municipality belongs.
 */
@Parcelize
@Serializable
data class Municipality(
    val code: String,
    val name: String,
    val province: String,
    val autonomousCommunity: String
) : Parcelable
