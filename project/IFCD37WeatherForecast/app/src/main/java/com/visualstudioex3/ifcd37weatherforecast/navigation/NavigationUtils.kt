package com.visualstudioex3.ifcd37weatherforecast.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.json.Json

/**
 * Navigation utils.
 */
object NavigationUtils {
    /**
     * Navigation type mapper.
     *
     * Its used to resolve the way to pass data as arguments between screens.
     * Source: [Medium - Pass data between screens with Jetpack Compose Navigation + Hilt](https://blog.eclypse.io/pass-data-between-screens-with-jetpack-compose-navigation-hilt-947c72d7dc4e)
     *
     * @param isNullableAllowed Allow nullable types.
     * @param json Serialized value.
     *
     * @return Returns [NavType] with the argument value.
     */
    inline fun <reified T : Parcelable?> navType(
        isNullableAllowed: Boolean = true,
        json: Json = Json,
    ) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String): T? =
            bundle.getParcelable(key, T::class.java)

        override fun parseValue(value: String): T {
            val deserializedResult = json.decodeFromString<T>(value)
            return deserializedResult
        }

        override fun serializeAsValue(value: T): String {
            return if (value == null) {
                ""
            } else {
                json.encodeToString(value)
            }
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            if (value == null) {
                bundle.putParcelable(key, null)
            } else {
                bundle.putParcelable(key, value)
            }
        }
    }
}
