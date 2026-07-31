package org.example.forecast.exceptions

/**
 * Forbidden exception (HTTP status code 403).
 *
 * @param customMessage Optional. Custom error message. By default is the standard HTTP 403 error message.
 */
class ForbiddenException(
    customMessage: String?
) : HttpRequestException(
    state = 403,
    message = customMessage ?: "Forbidden."
)