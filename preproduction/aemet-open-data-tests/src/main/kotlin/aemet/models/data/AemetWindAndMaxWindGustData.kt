package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetWindAndMaxWindGustData(
    @SerialName("direccion") val direction: List<String>?,
    @SerialName("velocidad") val velocity: List<Int>?,
    @SerialName("periodo") val period: String,
    val value: Int?,
)

/*
    FYI: This value is received as a couple of two different objects in the
    same list as follow:

    {
        "direccion": [
            "NO"
        ],
        "velocidad": [
            "22"
        ],
        "periodo": "21"
    },
    {
        "value": "33",
        "periodo": "21"
    }

    To be serialized as a type we used a data class with all fields as
    optional (except for 'period' that always is received) to fit the two
    combinations. 'period' field is used later as key to link the pair
    combinations.
 */