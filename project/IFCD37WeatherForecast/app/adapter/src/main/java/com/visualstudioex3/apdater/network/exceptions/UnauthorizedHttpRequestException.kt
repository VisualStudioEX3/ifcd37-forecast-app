package com.visualstudioex3.apdater.network.exceptions

/**
 * Unauthorized exception (HTTP status code 401).
 *
 * @param customMessage Optional. Custom error message. Left null to use default standard HTTP 401
 * error message.
 */
class UnauthorizedHttpRequestException(
    customMessage: String? = null
) : HttpRequestException(
    state = 401,
    message = customMessage ?: "Unauthorized."
)
