package com.visualstudioex3.application.ports.output

/**
 * Secrets service.
 */
internal interface SecretsService {
    /**
     * Get [String] secret value.
     *
     * @param key Secret key.
     *
     * @return Returns the [String] secret value or null if [key] not found.
     */
    fun getString(key: String): String?
}
