package com.visualstudioex3.apdater

import java.net.URL

internal object ResourceUtils {
    fun getResource(name: String): URL? =
        javaClass.classLoader.getResource(name)
}
