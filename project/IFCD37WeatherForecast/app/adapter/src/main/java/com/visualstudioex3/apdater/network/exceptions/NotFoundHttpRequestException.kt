package com.visualstudioex3.apdater.network.exceptions

/**
 * Not Found exception (HTTP status code 404).
 *
 * @param customMessage Optional. Custom error message. Left null to use default standard HTTP 404
 * error message.
 */
class NotFoundHttpRequestException(
    customMessage: String? = null
) : HttpRequestException(
    state = 404,
    message = customMessage ?: "Not Found."
)
