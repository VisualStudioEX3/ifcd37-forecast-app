package com.visualstudioex3.apdater.network.exceptions

/**
 * Forbidden exception (HTTP status code 403).
 *
 * @param customMessage Optional. Custom error message. Left null to use default standard HTTP 403
 * error message.
 */
class ForbiddenHttpRequestException(
    customMessage: String? = null
) : HttpRequestException(
    state = 403,
    message = customMessage ?: "Forbidden."
)
