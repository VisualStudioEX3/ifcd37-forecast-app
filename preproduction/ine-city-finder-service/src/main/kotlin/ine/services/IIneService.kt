package org.example.ine.services

import org.example.ine.models.IneCityData

/**
 * INE Service contract.
 *
 * Service to search for a places in Spain using the INE databases.
 */
interface IIneService {
    /**
     * Looks for cities that matches or contains the name.
     *
     * @param name Name of the city to search. The name can be incompleted to find all possible coincidences.
     *
     * @return Returns a list of [IneCityData] entries with the possible cities that fit the query.
     *
     * @throws IllegalArgumentException Throws if [name] is empty or blank string.
     */
    suspend fun findCitiesByName(name: String): List<IneCityData>

    /**
     * Gets state name by code.
     *
     * @return Returns the name of the state.
     *
     * @throws IllegalArgumentException Throws if [code] is empty or blank string.
     * @throws NoSuchElementException Throws if [code] not found.
     */
    suspend fun getStateNameByCode(code: String): String

    /**
     * Gets Autonomous Community name by code.
     *
     * @return Returns the name of the Autonomous Community.
     *
     * @throws IllegalArgumentException Throws if [code] is empty or blank string.
     * @throws NoSuchElementException Throws if [code] not found.
     */
    suspend fun getAutonomousCommunityNameByCode(code: String): String
}