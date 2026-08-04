package com.visualstudioex3.application.ports.input

import com.visualstudioex3.application.entities.Municipality

/**
 * Municipality finder service.
 */
interface MunicipalityFinder {
    /**
     * Looks for municipalities that matches or contains the name.
     *
     * @param name Municipality name. This value can be a full name or part of it.
     *
     * @return Returns a list of [Municipality] entries with the possible municipalities that
     * fit the query.
     *
     * @throws IllegalArgumentException Throws if [name] is empty or blank string.
     */
    fun findMunicipalities(name: String): List<Municipality>
}
