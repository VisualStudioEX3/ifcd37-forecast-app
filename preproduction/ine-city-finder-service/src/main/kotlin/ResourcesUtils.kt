package org.example

import java.net.URL

object ResourceUtils {
    fun getResource(name: String): URL? =
        javaClass.classLoader.getResource(name)
}