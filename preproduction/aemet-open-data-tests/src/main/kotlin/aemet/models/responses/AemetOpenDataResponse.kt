package org.example.aemet.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET OpenData API response model.
 *
 * This response model received sucessful responses and any error response from AEMET OpenData API.
 */
@Serializable
data class AemetOpenDataResponse(
    /**
     * AEMET OpenData response state.
     *
     * @return Usually 1.
     */
    @SerialName("estado") val state: Int,

    /**
     * Response description.
     *
     * In case of error, show a short message description.
     *
     * @return Short message with the meaning of the response.
     */
    @SerialName("descripcion") val description: String,

    /**
     * Temporal url to request the response data.
     *
     * @return If the response is succesful, then this field contains the url where to request the response data.
     * Otherwise empty [String] or null.
     */
    @SerialName("datos") val requestUrlData: String?,

    /**
     * Temporal url to request the response metadata.
     *
     * @return If the response is succesful, then this field contains the url where to request the response metadata.
     * This metadata shows the response model definition and details. Otherwise empty [String] or null.
     */
    @SerialName("metadatos") val requestUrlMetadata: String?,
)