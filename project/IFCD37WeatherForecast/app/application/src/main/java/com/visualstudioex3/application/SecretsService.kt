package com.visualstudioex3.application

/**
 * Secrets service.
 */
interface SecretsService {
    /**
     * Get [String] secret value.
     *
     * @param key Secret key.
     *
     * @return Returns the [String] secret value or null if [key] not found.
     */
    fun getString(key: String): String?
}
