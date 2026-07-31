package org.example

import java.util.*

object Secrets {
    private const val SECRETS: String = "secrets.properties"

    private val secrets = Properties()

    init {
        try {
            val file = javaClass.classLoader.getResourceAsStream(SECRETS)
                ?: error("'$SECRETS' file was not found. Check if the file exists in resources folder.")

            secrets.load(file)
        } catch (e: Exception) {
            error("Error loading '$SECRETS' file: ${e.message}")
        }
    }

    fun getSecret(key: String): String =
        secrets.getProperty(key) ?: error("Secret '$key' was not found.")
}