package org.example.forecast.exceptions

/**
 * Too Many Request exception (HTTP status code 429).
 *
 * @param customMessage Optional. Custom error message. By default is the standard HTTP 429 error message.
 */
class TooManyRequestsException(
    customMessage: String?
) : HttpRequestException(
    state = 429,
    message = customMessage ?: "Too Many Requests"
)