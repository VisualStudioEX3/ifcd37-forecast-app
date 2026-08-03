package com.visualstudioex3.application.ports.output

/**
 * Secrets service.
 */
// TODO: Make internal this service contract when Weather Forecast service is implemented.
/* internal */interface SecretsService {
    /**
     * Get [String] secret value.
     *
     * @param key Secret key.
     *
     * @return Returns the [String] secret value or null if [key] not found.
     */
    fun getString(key: String): String?
}
