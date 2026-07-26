package org.example.aemet.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AEMET source data.
 *
 * This data is only for information.
 */
@Serializable
data class AemetSourceData(
    /**
     * AEMET title.
     *
     * @return "Agencia Estatal de Meteorología - AEMET. Gobierno de España."
     */
    @SerialName("productor") val producer: String,

    /**
     * AEMET web site url.
     *
     * @return "https://www.aemet.es"
     */
    val web: String,

    /**
     * Link to online requested prediction in AEMET web site.
     *
     * @return The url to the requested prediction online.
     */
    @SerialName("enlace") val link: String,

    /**
     * Language used.
     *
     * @return "es"
     */
    val language: String,

    /**
     * AEMET copyright text.
     *
     * @return "© AEMET. Autorizado el uso de la información y su reproducción citando a AEMET como autora de la misma."
     */
    val copyright: String,

    /**
     * AEMET legal notes page url.
     *
     * @return "https://www.aemet.es/es/nota_legal/nota_legal"
     */
    @SerialName("notaLegal") val legalNotes: String,
)