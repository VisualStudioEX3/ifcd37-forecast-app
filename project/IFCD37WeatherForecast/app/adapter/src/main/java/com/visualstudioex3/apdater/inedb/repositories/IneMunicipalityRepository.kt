package com.visualstudioex3.apdater.inedb.repositories

import com.visualstudioex3.apdater.inedb.models.IneMunicipalityData

/**
 * INE municipality repository service.
 *
 * See: [INE - Relación de municipios y sus códigos por provincias. Últimos datos](https://www.ine.es/dyngs/INEbase/es/operacion.htm?c=Estadistica_C&cid=1254736177031&menu=ultiDatos&idp=1254734710990)
 */
interface IneMunicipalityRepository {
    /**
     * Looks for municipalities that matches or contains the name.
     *
     * @param name Name of the municipality to search. The name can be incompleted to find all
     * possible coincidences.
     *
     * @return Returns a list of [IneMunicipalityData] entries with the possible municipalities that
     * fit the query.
     *
     * @throws IllegalArgumentException Throws if [name] is empty or blank string.
     */
    fun findByName(name: String): List<IneMunicipalityData>
}
