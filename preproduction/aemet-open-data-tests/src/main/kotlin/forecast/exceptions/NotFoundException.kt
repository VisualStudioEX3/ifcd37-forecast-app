package org.example.forecast.exceptions

/**
 * Not Found exception (HTTP status code 404).
 *
 * @param customMessage Optional. Custom error message. By default is the standard HTTP 404 error message.
 */
class NotFoundException(
    customMessage: String?
) : HttpRequestException(
    state = 404,
    message = customMessage ?: "Not Found."
)