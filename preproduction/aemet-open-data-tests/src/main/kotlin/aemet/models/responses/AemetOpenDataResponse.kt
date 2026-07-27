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
     * HTTP response state.
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
     * Otherwise null.
     */
    @SerialName("datos") val requestUrlData: String? = null,

    /**
     * Temporal url to request the response metadata.
     *
     * @return If the response is succesful, then this field contains the url where to request the response metadata.
     * This metadata shows the response model definition and details. Otherwise null.
     */
    @SerialName("metadatos") val requestUrlMetadata: String? = null,
) {
    /**
     * Extracts the id value from [requestUrlData] value.
     *
     * @return String value with the data id.
     * @throws IllegalStateException Throws when [requestUrlData] is null.
     */
    fun getDataId(): String = requestUrlData?.split('/')?.last()
        ?: error("Request URL data is null.")

    /**
     * Extracts the id value from [requestUrlMetadata] value.
     *
     * @return String value with the metadadata id.
     * @throws IllegalStateException Throws when [requestUrlMetadata] is null.
     */
    fun getMetaDataId(): String = requestUrlMetadata?.split('/')?.last()
        ?: error("Request URL data is null.")
}