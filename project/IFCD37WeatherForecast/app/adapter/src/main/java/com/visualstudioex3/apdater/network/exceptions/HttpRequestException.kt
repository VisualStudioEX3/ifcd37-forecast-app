package com.visualstudioex3.apdater.network.exceptions

/**
 * HTTP request exception.
 *
 * @param state HTTP status code.
 * @param message HTTP status message.
 */
open class HttpRequestException(
    val state: Int,
    message: String
) : RuntimeException(message)
