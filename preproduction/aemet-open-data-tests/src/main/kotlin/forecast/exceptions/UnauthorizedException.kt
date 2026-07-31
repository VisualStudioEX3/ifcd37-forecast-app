package org.example.forecast.exceptions

/**
 * Unauthorized exception (HTTP status code 401).
 *
 * @param customMessage Optional. Custom error message. By default is the standard HTTP 401 error message.
 */
class UnauthorizedException(
    customMessage: String?
) : HttpRequestException(
    state = 401,
    message = customMessage ?: "Unauthorized."
)