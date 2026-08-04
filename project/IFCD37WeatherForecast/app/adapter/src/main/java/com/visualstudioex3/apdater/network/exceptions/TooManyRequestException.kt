package com.visualstudioex3.apdater.network.exceptions

/**
 * Too Many Request exception (HTTP status code 429).
 *
 * @param customMessage Optional. Custom error message. Left null to use default standard HTTP 429
 * error message.
 */
class TooManyRequestsHttpRequestException(
    customMessage: String? = null
) : HttpRequestException(
    state = 429,
    message = customMessage ?: "Too Many Requests"
)
