package com.visualstudioex3.apdater.network

import java.net.URI
import java.nio.charset.Charset

/**
 * Network utils service.
 */
internal object NetworkUtils {
    /**
     * Downloads a remote resource as string.
     *
     * @param url Url to the requested data.
     * @param encoding Optional. Character encoding. By default is [Charsets.UTF_8].
     *
     * @throws java.net.URISyntaxException If the given string violates RFC 2396, as augmented by the above deviations.
     * @throws IllegalArgumentException If this [url] is not absolute.
     * @throws java.net.MalformedURLException If a protocol handler for the [url] could not be found, or if some other error
     * occurred while constructing the URL.
     */
    fun downloadResourceStringFromUrl(
        url: String,
        encoding: Charset = Charsets.UTF_8
    ): String =
        URI(url).toURL().readText(encoding)
}
